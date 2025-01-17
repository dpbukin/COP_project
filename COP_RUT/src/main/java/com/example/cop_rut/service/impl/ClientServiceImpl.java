package com.example.cop_rut.service.impl;

import com.example.cop_rut.config.RabbitMQConfig;
import com.example.cop_rut.dtos.EventLogDto;
import com.example.cop_rut.dtos.base.ClientDto;
import com.example.cop_rut.model.Client;
import com.example.cop_rut.repositories.ClientRepository;
import com.example.cop_rut.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final RabbitTemplate rabbitTemplate;
    private ClientRepository clientRepository;
    private ModelMapper modelMapper;
    private NotificationService notificationService;

    @Autowired
    public ClientServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);

        EventLogDto log = new EventLogDto(
                client.getUuid().toString(),
                "SAVE",
                LocalDateTime.now(),
                "Client saved: " + client.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

    }

    @Override
    public ClientDto add(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        client.setUuid(String.valueOf(UUID.randomUUID()));
        clientRepository.save(client);
        client.setCreateDate(LocalDateTime.now());
        client.setUpdateDate(LocalDateTime.now());

        EventLogDto log = new EventLogDto(
                client.getUuid().toString(),
                "CREATE",
                LocalDateTime.now(),
                "Client created: " + client.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);


        String notificationMessage = String.format(
                "Клиент с UUID %s был создан, дата: %s",
                client.getUuid(),
                client.getCreateDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        );
        notificationService.sendNotification(notificationMessage);

        return clientDto;
    }

    @Override
    public ClientDto getClientByUuid(String uuid) {
        return modelMapper.map(clientRepository.findClientByUuid(uuid).orElse(null), ClientDto.class);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findClientsByPhoneNumber(String phoneNumber) {
        return modelMapper.map(clientRepository.findByPhoneNumber(phoneNumber), ClientDto.class) == null ? null : modelMapper.map(clientRepository.findByPhoneNumber(phoneNumber), ClientDto.class);
//        return Optional.ofNullable(modelMapper.map(clientRepository.findByPhoneNumber(phoneNumber), ClientDto.class));
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientRepository.findClientByUuid(clientDto.getUuid()).orElseThrow(), Client.class);

        if(clientDto.getName() != null){
            client.setName(clientDto.getName());
        }
        if(clientDto.getPhoneNumber() != null){
            client.setPhoneNumber(clientDto.getPhoneNumber());
        }
        if(clientDto.getEmail() != null){
            client.setEmail(clientDto.getEmail());
        }
        if(clientDto.getOrders() != null){
            client.setOrders(clientDto.getOrders());
        }

        client.setUpdateDate(LocalDateTime.now());
        clientRepository.save(client);

        EventLogDto log = new EventLogDto(
                client.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Client updated: " + client.toString()
        );

        System.out.println(log);
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);


        return clientDto;
    }

    @Override
    public void addOrderInClient(String clientId, String orderId) {
        Client client = clientRepository.findClientByUuid(clientId).orElseThrow();
        List<String> orders = client.getOrders();
        orders.add(orderId);
        client.setOrders(orders);
        clientRepository.save(client);

        EventLogDto log = new EventLogDto(
                client.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Client updated: " + client.toString()
        );

        System.out.println(log);
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

    }

    @Override
    public void deleteClient(String uuid) {
        Client client = clientRepository.findClientByUuid(uuid).orElseThrow();
        client.setDelete(true);
        clientRepository.save(client);

        EventLogDto log = new EventLogDto(
                client.getId().toString(),
                "DELETE",
                LocalDateTime.now(),
                "Client deleted: " + client.toString()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
