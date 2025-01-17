package com.example.cop_rut.service;

import com.example.cop_rut.dtos.base.WorkerDto;
import com.example.cop_rut.model.Worker;

import java.util.List;

public interface WorkerService {
    void save(Worker worker);
    WorkerDto add(WorkerDto workerDto);
    WorkerDto getWorkerByUuid(String uuid);
    List<WorkerDto> getAllWorkers();
    List<WorkerDto> getWorkersByBrigade(String brigadeId);
    List<WorkerDto> getWorkersOnShift();
    WorkerDto updateWorker(WorkerDto workerDto);
    void updateWorkerStatus(String workerId, Boolean status);
    void dismissedWorkerByUuid(String uuid);
}
