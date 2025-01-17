package com.example.cop_rut_contracts.controllers;

import com.example.cop_rut_contracts.dtos.base.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "Клиенты", description = "API для работы с клиентами")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Успешная обработка запроса"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public interface ClientApi {

    @Operation(summary = "Создать клиента")
    @PostMapping(value = "/api/client/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createClient(@Valid @RequestBody ClientDto clientRequest);

    @Operation(summary = "Получить клиента по ID")
    @GetMapping(value = "/api/client/get/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto getClientById(@PathVariable("uuid") String uuid);

    @Operation(summary = "Получить всех клиентов")
    @GetMapping(value = "/api/client/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> getAllClients();

    @Operation(summary = "Получить клиента по номеру телефона")
    @GetMapping(value = "/api/client/phone/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findClientsByPhoneNumber(@PathVariable String phoneNumber);

    @Operation(summary = "Обновить информацию о клиенте")
    @PutMapping(value = "/api/client/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateClient(@Valid @RequestBody ClientDto clientRequest);

    @Operation(summary = "Удалить клиента по ID")
    @DeleteMapping(value = "/api/client/del/{uuid}")
    void deleteClientById(@PathVariable("uuid") String uuid);

    @Operation(summary = "Добавить заказ для клиента")
    @PostMapping(value = "/api/client/{clientId}/order/{orderId}")
    void addOrderToClient(@PathVariable("clientId") String clientId, @PathVariable("orderId") String orderId);
}

