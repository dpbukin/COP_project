package com.example.cop_rut.service.impl;

import com.example.cop_rut.config.RabbitMQConfig;
import com.example.cop_rut.dtos.EventLogDto;
import com.example.cop_rut.dtos.base.OrderDto;
import com.example.cop_rut.dtos.base.SpaceDto;
import com.example.cop_rut.model.Order;
import com.example.cop_rut.model.Space;
import com.example.cop_rut.model.enam.order.ExecutionStatus;
import com.example.cop_rut.repositories.OrderRepository;
import com.example.cop_rut.service.BrigadeService;
import com.example.cop_rut.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private BrigadeService brigadeService;
    private RabbitTemplate rabbitTemplate;
    private ModelMapper modelMapper;
    private MongoTemplate mongoTemplate;
    private NotificationService notificationService;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Override
    public void save(Order order) {
        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid().toString(),
                "SAVE",
                LocalDateTime.now(),
                "Заказ сохранен: " + order.toString()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

    }

    @Override
    public OrderDto add(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        String uuid = String.valueOf(UUID.randomUUID());
        order.setUuid(uuid);
        order.setExecutionStatus(ExecutionStatus.ACCEPTED);
        order.setArchived(false);
        order.setCreateDate(LocalDateTime.now());
        order.setUpdateDate(LocalDateTime.now());

        String freeBrigade = null;
        try {
            freeBrigade = brigadeService.assignBrigadeToOrder(uuid);
        } catch (Exception e) {
            EventLogDto log = new EventLogDto(
                    order.getUuid(),
                    "EXEPTION",
                    LocalDateTime.now(),
                    "Order exeption: " + "Не удалось получить свободную бригаду для заказа с UUID: \"uuid"
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

            System.out.println("Не удалось получить свободную бригаду для заказа с UUID: " + uuid);
        }

        if (freeBrigade != null) {
            order.setBrigade(freeBrigade);
        }

        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid(),
                "CREATE",
                LocalDateTime.now(),
                "Заказ создан: " + order.toString()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

        String notificationMessage = String.format(
                "Заказ с UUID %s был создан, дата: %s, статус: %s",
                order.getUuid(), order.getCreateDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), order.getExecutionStatus());
        notificationService.sendNotification(notificationMessage);


        notificationService.sendNotification(notificationMessage);

        if (freeBrigade == null) {
            scheduleRetryForAssigningBrigade(order);
        }

        return orderDto;
    }

    private void scheduleRetryForAssigningBrigade(Order order) {
        scheduledExecutorService.schedule(() -> {
            try {
                String freeBrigade = brigadeService.assignBrigadeToOrder(order.getUuid());
                if (freeBrigade != null) {
                    order.setBrigade(freeBrigade);
                    orderRepository.save(order);

                    EventLogDto log = new EventLogDto(
                            order.getUuid(),
                            "UPDATE",
                            LocalDateTime.now(),
                            "Order update: " + "Бригада была успешно назначена заказу с UUID: " + order.getUuid()
                    );
                    rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

                    System.out.println("Бригада была успешно назначена заказу с UUID: " + order.getUuid());
                } else {

                    EventLogDto log = new EventLogDto(
                            order.getUuid(),
                            "EXEPTION",
                            LocalDateTime.now(),
                            "Order exeption: " + "Не удалось получить свободную бригаду для заказа с UUID: " + order.getUuid()
                    );

                    rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
                    System.out.println("Не удалось назначить бригаду заказу с UUID: " + order.getUuid() + ", попробуем снова позже");

                    scheduleRetryForAssigningBrigade(order);
                }
            } catch (Exception e) {
                EventLogDto log = new EventLogDto(
                        order.getUuid(),
                        "EXEPTION",
                        LocalDateTime.now(),
                        "Order exeption: " + "Ошибка при повторной попытке назначения бригады для заказа с UUID: " + order.getUuid() + " " + e
                );
                rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

                System.out.println("Ошибка при повторной попытке назначения бригады для заказа с UUID: " + order.getUuid() + " " + e);
            }
        }, 10, TimeUnit.MINUTES);
    }

    @Override
    public OrderDto getOrderByUuid(String uuid) {
        return modelMapper.map(orderRepository.findOrderByUuid(uuid).orElse(null), OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByClientUuid(String clientUuid) {
        return orderRepository.findOrderByClient(clientUuid).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByExecutionDate(LocalDateTime executionDate) {
        return orderRepository.findOrderByExecutionDate(executionDate).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderRepository.findOrderByUuid(orderDto.getUuid()), Order.class);

        if (orderDto.getCleaningType() != null) {
            order.setCleaningType(orderDto.getCleaningType());
        }
        if (orderDto.getExecutionStatus() != null) {
            order.setExecutionStatus(orderDto.getExecutionStatus());
        }
        if (orderDto.getExecution() != null) {
            order.setExecutionDate(orderDto.getExecution());
        }
        if (orderDto.getCreateDate() != null) {
            order.setCreateDate(orderDto.getCreateDate());
        }
        if (orderDto.getOrderNumber() != null) {
            order.setOrderNumber(orderDto.getOrderNumber());
        }
        if (orderDto.getDistanceFromCenter() != null) {
            order.setDistanceFromCenter(orderDto.getDistanceFromCenter());
        }
        if (orderDto.getAdditionalServices() != null) {
            order.setAdditionalServices(orderDto.getAdditionalServices());
        }
        if (orderDto.getCustomerComment() != null) {
            order.setCustomerComment(orderDto.getCustomerComment());
        }
        if (orderDto.getExecutorComment() != null) {
            order.setExecutorComment(orderDto.getExecutorComment());
        }
        if (orderDto.getRedFlag() != null) {
            order.setRedFlag(orderDto.getRedFlag());
        }
        if (orderDto.getCost() != null) {
            order.setCost(orderDto.getCost());
        }

        order.setArchived(orderDto.isArchived());

        if (orderDto.getSpace() != null) {
            order.setSpace(orderDto.getSpace().stream()
                    .map(space -> modelMapper.map(space, Space.class))
                    .collect(Collectors.toList()));
        }

        if (orderDto.getClient() != null) {
            order.setClient(orderDto.getClient());
        }
        if (orderDto.getBrigade() != null) {
            order.setBrigade(orderDto.getBrigade());
        }

        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Заказ обновлен: " + order.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

        String notificationMessage = String.format(
                "Заказ с UUID %s был обновлен, дата: %s, статус: %s",
                order.getUuid(), order.getCreateDate(), order.getExecutionStatus());
        notificationService.sendNotification(notificationMessage);

        return orderDto;
    }

    @Override
    public List<OrderDto> getOrderByBrigadeUuid(String brigadeId) {
        return orderRepository.findOrdersByBrigadeId(brigadeId).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderExecutionStatus(String orderId, ExecutionStatus executionStatus) {
        Order order = orderRepository.findOrderByUuid(orderId).orElseThrow();

        if (executionStatus == ExecutionStatus.COMPLETED) {
            brigadeService.freeBrigade(order.getBrigade());
            order.setExecutionDate(LocalDateTime.now());
        }

        order.setExecutionStatus(executionStatus);
        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Статус заказа был изменен: " + order.toString()
        );

        String notificationMessage = String.format(
                "Заказ с UUID %s обновлен, дата: %s, статус: %s",
                order.getUuid(),
                LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")),
                order.getExecutionStatus());

        notificationService.sendNotification(notificationMessage);

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
    }

    @Override
    public void addSpaceInOrderSpaceList(String orderId, SpaceDto spaceDto) {
        Order order = orderRepository.findOrderByUuid(orderId).orElseThrow();
        List<Space> spaces = order.getSpace();
        spaces.add(modelMapper.map(spaceDto, Space.class));
        order.setSpace(spaces);
        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Помещения в заказе обновлены: " + order.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
    }

    @Override
    public void ArchivedOrder(String uuid) {
        Order order = orderRepository.findOrderByUuid(uuid).orElseThrow();
        order.setArchived(true);
        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid().toString(),
                "ARCHIVED",
                LocalDateTime.now(),
                "Заказ добавлен в архив: " + order.toString()
        );

        String notificationMessage = String.format(
                "Заказ с UUID %s добавлен в архив, дата: %s, статус: %s",
                order.getUuid(),
                LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")),
                order.getExecutionStatus());

        notificationService.sendNotification(notificationMessage);

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

    }

    @Override
    public void UnArchivedOrder(String orderId) {
        Order order = orderRepository.findOrderByUuid(orderId).orElseThrow();
        order.setArchived(false);
        orderRepository.save(order);

        EventLogDto log = new EventLogDto(
                order.getUuid().toString(),
                "UNARCHIVED",
                LocalDateTime.now(),
                "Заказ стал активным: " + order.toString()
        );

        String notificationMessage = String.format(
                "Заказ с UUID %s активен, дата: %s, статус: %s",
                order.getUuid(),
                LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")),
                order.getExecutionStatus());

        notificationService.sendNotification(notificationMessage);

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setBrigadeService(BrigadeService brigadeService) {
        this.brigadeService = brigadeService;
    }
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
