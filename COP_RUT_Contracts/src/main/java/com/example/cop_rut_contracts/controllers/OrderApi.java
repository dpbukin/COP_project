package com.example.cop_rut_contracts.controllers;

import com.example.cop_rut_contracts.dtos.base.OrderDto;
import com.example.cop_rut_contracts.dtos.base.SpaceDto;
import com.example.cop_rut_contracts.enam.order.ExecutionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@Tag(name = "orders", description = "API для работы с заказами")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Успешная обработка запроса"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public interface OrderApi {

    @Operation(summary = "Создать заказ")
    @PostMapping(value = "/api/order/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createOrder(@RequestBody OrderDto orderRequest);

    @Operation(summary = "Получить заказ по ID")
    @GetMapping(value = "/api/order/get", produces = MediaType.APPLICATION_JSON_VALUE)
    OrderDto getOrderById(@RequestParam String uuid);

    @Operation(summary = "Получить все заказы")
    @GetMapping(value = "/api/order/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OrderDto> getAllOrders();

    @Operation(summary = "Получить заказы по ID клиента")
    @GetMapping(value = "/api/order/client", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OrderDto> getOrdersByClientId(@RequestParam String clientId);

    @Operation(summary = "Получить заказы по дате исполнения")
    @GetMapping(value = "/api/order/execution", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OrderDto> getOrdersByExecutionDate(@RequestParam LocalDateTime executionDate);

    @Operation(summary = "Обновить заказ")
    @PutMapping(value = "/api/order/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateOrder(@RequestBody OrderDto orderRequest);

    @Operation(summary = "Удалить заказ по ID")
    @DeleteMapping(value = "/api/order/archived/{id}")
    void deleteOrderById(@PathVariable String uuid);

    @Operation(summary = "Добавить пространство в список пространств заказа")
    @PostMapping(value = "/api/order/order/{orderId}/space", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addSpaceInOrderSpaceList(@PathVariable String orderId, @RequestBody SpaceDto spaceDto);

    @Operation(summary = "Обновить статус выполнения заказа")
    @PutMapping(value = "/api/order/status")
    void updateOrderExecutionStatus(@RequestParam String orderId, @RequestParam ExecutionStatus executionStatus);
}


