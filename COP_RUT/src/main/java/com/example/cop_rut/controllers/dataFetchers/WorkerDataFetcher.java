package com.example.cop_rut.controllers.dataFetchers;

import com.example.cop_rut.dtos.base.WorkerDto;
import com.example.cop_rut.service.WorkerService;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@DgsComponent
public class WorkerDataFetcher {

    private WorkerService workerService;

    @DgsQuery
    public WorkerDto getWorkerById(@InputArgument String uuid) {
        return workerService.getWorkerByUuid(uuid);
    }

    @DgsQuery
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @DgsQuery
    public List<WorkerDto> getWorkersByBrigade(@InputArgument String brigadeUuid) {
        return workerService.getWorkersByBrigade(brigadeUuid);
    }

    @DgsQuery
    public List<WorkerDto> getWorkersOnShift() {
        return workerService.getWorkersOnShift();
    }

    @DgsMutation
    public WorkerDto createWorker(
            @InputArgument String name,
            @InputArgument String phoneNumber,
            @InputArgument Boolean isOnShift,
            @InputArgument String brigade,
            @InputArgument Boolean dismissed
    ) {
        WorkerDto workerDto = new WorkerDto();
        workerDto.setName(name);
        workerDto.setPhoneNumber(phoneNumber);
        workerDto.setOnShift(isOnShift);
        workerDto.setBrigade(brigade);
        workerDto.setDismissed(dismissed);

        return workerService.add(workerDto);
    }

    @DgsMutation
    public WorkerDto updateWorker(
            @InputArgument String uuid,
            @InputArgument String name,
            @InputArgument String phoneNumber,
            @InputArgument Boolean isOnShift,
            @InputArgument String brigade,
            @InputArgument Boolean dismissed
    ) {
        WorkerDto workerDto = workerService.getWorkerByUuid(uuid);

        if (name != null) {
            workerDto.setName(name);
        }
        if (phoneNumber != null) {
            workerDto.setPhoneNumber(phoneNumber);
        }
        workerDto.setOnShift(isOnShift);

        if (brigade != null) {
            workerDto.setBrigade(brigade);
        }
        workerDto.setDismissed(dismissed);

        return workerService.updateWorker(workerDto);
    }

    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}

