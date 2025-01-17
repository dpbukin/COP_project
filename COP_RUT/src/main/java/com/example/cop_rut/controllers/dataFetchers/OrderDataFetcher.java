package com.example.cop_rut.controllers.dataFetchers;

import com.example.cop_rut.dtos.base.OrderDto;
import com.example.cop_rut.dtos.base.SpaceDto;
import com.example.cop_rut.model.enam.order.*;
import com.example.cop_rut.service.OrderService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DgsComponent
public class OrderDataFetcher {

    private OrderService orderService;

    @DgsQuery
    public OrderDto getOrderById(@InputArgument String uuid) {
        return orderService.getOrderByUuid(uuid);
    }

    @DgsQuery
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DgsQuery
    public List<OrderDto> getOrdersByClientId(@InputArgument String uuid) {
        return orderService.getOrdersByClientUuid(uuid);
    }

    @DgsQuery
    public List<OrderDto> getOrdersByExecutionDate(@InputArgument String executionDate) {
        return orderService.getOrdersByExecutionDate(LocalDate.from(LocalDateTime.parse(executionDate)).atStartOfDay());
    }

    @DgsMutation
    public OrderDto createOrder(
            @InputArgument CleaningType cleaningType,
            @InputArgument ExecutionStatus executionStatus,
            @InputArgument String execution,
            @InputArgument String orderNumber,
            @InputArgument DistanceFromCenter distanceFromCenter,
            @InputArgument List<AdditionalService> additionalServices,
            @InputArgument String customerComment,
            @InputArgument String executorComment,
            @InputArgument RedFlag redFlag,
            @InputArgument Float cost,
            @InputArgument Boolean archived,
            @InputArgument List<SpaceDto> spaces,
            @InputArgument String client,
            @InputArgument String brigade) {

        OrderDto orderDto = new OrderDto();

        if (cleaningType != null) {
            orderDto.setCleaningType(cleaningType);
        }
        if (executionStatus != null) {
            orderDto.setExecutionStatus(executionStatus);
        }
        if (execution != null) {
            orderDto.setExecution(LocalDate.parse(execution).atStartOfDay());
        }
        if (orderNumber != null) {
            orderDto.setOrderNumber(orderNumber);
        }
        if (distanceFromCenter != null) {
            orderDto.setDistanceFromCenter(distanceFromCenter);
        }
        if (additionalServices != null) {
            orderDto.setAdditionalServices(additionalServices);
        }
        if (customerComment != null) {
            orderDto.setCustomerComment(customerComment);
        }
        if (executorComment != null) {
            orderDto.setExecutorComment(executorComment);
        }
        if (redFlag != null) {
            orderDto.setRedFlag(redFlag);
        }
        if (cost != null) {
            orderDto.setCost(BigDecimal.valueOf(cost));
        }
        if (archived != null) {
            orderDto.setArchived(archived);
        }
        if (spaces != null) {
            orderDto.setSpace(spaces);
        }
        if (client != null) {
            orderDto.setClient(client);
        }
        if (brigade != null) {
            orderDto.setBrigade(brigade);
        }

        return orderService.updateOrder(orderDto);
    }

    @DgsMutation
    public OrderDto updateOrder(
            @InputArgument String uuid,
            @InputArgument CleaningType cleaningType,
            @InputArgument ExecutionStatus executionStatus,
            @InputArgument String execution,
            @InputArgument String orderNumber,
            @InputArgument DistanceFromCenter distanceFromCenter,
            @InputArgument List<AdditionalService> additionalServices,
            @InputArgument String customerComment,
            @InputArgument String executorComment,
            @InputArgument RedFlag redFlag,
            @InputArgument Float cost,
            @InputArgument Boolean archived,
            @InputArgument List<SpaceDto> spaces,
            @InputArgument String client,
            @InputArgument String brigade) throws Exception {

        OrderDto orderDto = orderService.getOrderByUuid(uuid);
        if (orderDto == null) {
            throw new Exception("Order with UUID " + uuid + " not found");
        }
        // Проверяем каждое поле на null перед обновлением
        if (cleaningType != null) {
            orderDto.setCleaningType(cleaningType);
        }
        if (executionStatus != null) {
            orderDto.setExecutionStatus(executionStatus);
        }
        if (execution != null) {
            orderDto.setExecution(LocalDate.parse(execution).atStartOfDay());
        }
        if (orderNumber != null) {
            orderDto.setOrderNumber(orderNumber);
        }
        if (distanceFromCenter != null) {
            orderDto.setDistanceFromCenter(distanceFromCenter);
        }
        if (additionalServices != null) {
            orderDto.setAdditionalServices(additionalServices);
        }
        if (customerComment != null) {
            orderDto.setCustomerComment(customerComment);
        }
        if (executorComment != null) {
            orderDto.setExecutorComment(executorComment);
        }
        if (redFlag != null) {
            orderDto.setRedFlag(redFlag);
        }
        if (cost != null) {
            orderDto.setCost(BigDecimal.valueOf(cost));
        }
        if (archived != null) {
            orderDto.setArchived(archived);
        }
        if (spaces != null) {
            orderDto.setSpace(spaces);
        }
        if (client != null) {
            orderDto.setClient(client);
        }
        if (brigade != null) {
            orderDto.setBrigade(brigade);
        }

        return orderService.updateOrder(orderDto);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}