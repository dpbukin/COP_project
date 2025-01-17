package com.example.cop_rut_contracts.controllers;

import com.example.cop_rut_contracts.dtos.base.BrigadeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "brigades", description = "API для работы с бригадами")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Успешная обработка запроса"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public interface BrigadeApi {
    @Operation(summary = "Создать бригаду")
    @PostMapping(value = "/api/brigades", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createBrigade(@Valid @RequestBody BrigadeDto brigadeRequest);

    @Operation(summary = "Получить бригаду по ID")
    @GetMapping(value = "/api/brigades/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    BrigadeDto getBrigade(@PathVariable("uuid") String uuid);

    @Operation(summary = "Получить все бригады")
    @GetMapping(value = "/api/brigades/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BrigadeDto> getAllBrigades();

    @Operation(summary = "Получить свободные бригады")
    @GetMapping(value = "/api/brigades/free", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BrigadeDto> getFreeBrigades();

    @Operation(summary = "Обновить информацию о бригаде")
    @PutMapping(value = "/api/brigades/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateBrigade(@Valid @RequestBody BrigadeDto brigadeRequest);

    @Operation(summary = "Удалить бригаду по ID")
    @DeleteMapping(value = "/api/brigades/{uuid}")
    void removeBrigade(@PathVariable("uuid") String uuid);

    @Operation(summary = "Освободить бригаду")
    @PostMapping(value = "/api/brigades/{uuid}/free")
    String freeBrigade(@PathVariable("uuid") String uuid);

    @Operation(summary = "Назначить бригаду на заказ")
    @PostMapping(value = "/api/brigades/{uuid}/assign/{orderId}")
    String assignBrigadeToOrder(@PathVariable("uuid") String uuid, @PathVariable("orderId") String orderId);
}
