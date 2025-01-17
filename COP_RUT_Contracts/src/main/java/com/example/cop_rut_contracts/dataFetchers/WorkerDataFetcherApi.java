package com.example.cop_rut_contracts.dataFetchers;

import com.example.cop_rut_contracts.dtos.base.WorkerDto;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@DgsComponent
@Schema(description = "GraphQL API для работы с сотрудниками")
public interface WorkerDataFetcherApi {

    @DgsQuery
    @Schema(description = "Получить информацию о сотруднике по ID")
    WorkerDto getWorkerById(@InputArgument String uuid);

    @DgsQuery
    @Schema(description = "Получить список всех сотрудников")
    List<WorkerDto> getAllWorkers();

    @DgsQuery
    @Schema(description = "Получить сотрудников по ID бригады")
    List<WorkerDto> getWorkersByBrigade(@InputArgument String brigadeUuid);

    @DgsQuery
    @Schema(description = "Получить список сотрудников на смене")
    List<WorkerDto> getWorkersOnShift();

    @DgsMutation
    @Schema(description = "Создать нового сотрудника")
    WorkerDto createWorker(
            @InputArgument String name,
            @InputArgument String phoneNumber,
            @InputArgument Boolean isOnShift,
            @InputArgument String brigade,
            @InputArgument Boolean dismissed
    );

    @DgsMutation
    @Schema(description = "Обновить информацию о сотруднике")
    WorkerDto updateWorker(
            @InputArgument String uuid,
            @InputArgument String name,
            @InputArgument String phoneNumber,
            @InputArgument Boolean isOnShift,
            @InputArgument String brigade,
            @InputArgument Boolean dismissed
    );
}
