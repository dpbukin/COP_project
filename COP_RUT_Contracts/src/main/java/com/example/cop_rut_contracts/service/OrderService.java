package com.example.cop_rut_contracts.service;


import com.example.cop_rut_contracts.dtos.base.OrderDto;
import com.example.cop_rut_contracts.dtos.base.SpaceDto;
import com.example.cop_rut_contracts.enam.order.ExecutionStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
//    void save(Order order);
    OrderDto add(OrderDto orderDto);
    OrderDto getOrderByUuid(String orderId);
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersByClientUuid(String clientId);
    List<OrderDto> getOrdersByExecutionDate(LocalDateTime executionDate);
    OrderDto updateOrder(OrderDto orderDto);
    List<OrderDto> getOrderByBrigadeUuid(String brigadeId);
    void updateOrderExecutionStatus(String orderId, ExecutionStatus executionStatus);
    void addSpaceInOrderSpaceList(String orderId, SpaceDto spaceDto);
    void ArchivedOrder(String orderId);
    void UnArchivedOrder(String orderId);

}
