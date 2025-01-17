package com.example.cop_rut_contracts.controllers;

import com.example.cop_rut_contracts.dtos.base.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "workers", description = "API для работы с работниками")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Успешная обработка запроса"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public interface WorkerApi {

    @Operation(summary = "Создать работника")
    @PostMapping(value = "/api/worker/add", consumes = "application/json")
    OrderDto createWorker(@RequestBody OrderDto workerRequest);

    @Operation(summary = "Получить работника по UUID")
    @GetMapping(value = "/api/worker/get/{uuid}", produces = "application/json")
    OrderDto getWorkerById(@PathVariable String uuid);

    @Operation(summary = "Получить всех работников")
    @GetMapping(value = "/api/worker/all", produces = "application/json")
    List<OrderDto> getAllWorkers();

    @Operation(summary = "Получить работников по бригаде")
    @GetMapping(value = "/api/worker/brigade/{brigadeId}", produces = "application/json")
    List<OrderDto> getWorkersByBrigade(@PathVariable String brigadeId);

    @Operation(summary = "Получить работников на смене")
    @GetMapping(value = "/api/worker/shift", produces = "application/json")
    List<OrderDto> getWorkersOnShift();

    @Operation(summary = "Обновить работника")
    @PutMapping(value = "/api/worker/update", consumes = "application/json")
    OrderDto updateWorker(@RequestBody OrderDto workerRequest);

    @Operation(summary = "Удалить работника по UUID")
    @DeleteMapping(value = "/api/worker/dismissed/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Void deleteWorkerById(@PathVariable String uuid);

    @Operation(summary = "Обновить статус работника")
    @PutMapping(value = "/api/worker/update/status/{workerId}")
    OrderDto updateWorkerStatus(@PathVariable String workerId, @RequestBody Boolean status);
}
