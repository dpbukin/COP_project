package com.example.cop_rut_contracts.service;


import com.example.cop_rut_contracts.dtos.base.WorkerDto;

import java.util.List;

public interface WorkerService {
//    void save(Worker worker);
    WorkerDto add(WorkerDto workerDto);
    WorkerDto getWorkerByUuid(String uuid);
    List<WorkerDto> getAllWorkers();
    List<WorkerDto> getWorkersByBrigade(String brigadeId);
    List<WorkerDto> getWorkersOnShift();
    WorkerDto updateWorker(WorkerDto workerDto);
    void updateWorkerStatus(String workerId, Boolean status);
    void dismissedWorkerByUuid(String uuid);
}
