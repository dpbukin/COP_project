package com.example.cop_rut.service.impl;

import com.example.cop_rut.config.RabbitMQConfig;
import com.example.cop_rut.dtos.EventLogDto;
import com.example.cop_rut.dtos.base.WorkerDto;
import com.example.cop_rut.model.Worker;
import com.example.cop_rut.repositories.WorkerRepository;
import com.example.cop_rut.service.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final RabbitTemplate rabbitTemplate;
    private WorkerRepository workerRepository;
    private ModelMapper modelMapper;
    private NotificationService notificationService;

    @Autowired
    public WorkerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void save(Worker worker) {
        workerRepository.save(worker);

        EventLogDto log = new EventLogDto(
                worker.getUuid().toString(),
                "SAVE",
                LocalDateTime.now(),
                "Worker saved: " + worker.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

    }
    @Override
    public WorkerDto add(WorkerDto workerDto) {
        Worker worker = modelMapper.map(workerDto, Worker.class);
        worker.setUuid(String.valueOf(UUID.randomUUID()));
        worker.setOnShift(false);
        worker.setDismissed(false);
        worker.setCreateDate(LocalDateTime.now());
        worker.setUpdateDate(LocalDateTime.now());
        workerRepository.save(worker);
        EventLogDto log = new EventLogDto(
                worker.getUuid().toString(),
                "CREATE",
                LocalDateTime.now(),
                "Worker created: " + worker.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

        return workerDto;
    }

    @Override
    public WorkerDto getWorkerByUuid(String uuid) {
         return modelMapper.map(workerRepository.findWorkerByUuid(uuid).orElse(null), WorkerDto.class);
    }

    @Override
    public List<WorkerDto> getAllWorkers() {
        return workerRepository.findAll().stream()
                .map(worker -> modelMapper.map(worker, WorkerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerDto> getWorkersByBrigade(String brigadeUuid) {
        return workerRepository.findWorkersByBrigade(brigadeUuid).stream()
                .map(worker -> modelMapper.map(worker, WorkerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerDto> getWorkersOnShift() {
        return workerRepository.findWorkersByIsOnShift(true).stream()
                .map(worker -> modelMapper.map(worker, WorkerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public WorkerDto updateWorker(WorkerDto workerDto) {
        Worker worker = modelMapper.map(workerRepository.findWorkerByUuid(workerDto.getUuid()).orElseThrow(), Worker.class);

        if (workerDto.getName() != null) {
            worker.setName(workerDto.getName());
        }
        if (workerDto.getPhoneNumber() != null) {
            worker.setPhoneNumber(workerDto.getPhoneNumber());
        }
        worker.setOnShift(workerDto.isOnShift());

        if (workerDto.getBrigade() != null) {
            worker.setBrigade(workerDto.getBrigade());
        }
        worker.setDismissed(workerDto.isDismissed());
        worker.setUpdateDate(LocalDateTime.now());

        workerRepository.save(worker);

        EventLogDto log = new EventLogDto(
                worker.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Worker updated: " + worker.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

        return modelMapper.map(worker, WorkerDto.class);
    }

    @Override
    public void updateWorkerStatus(String workerId, Boolean status) {
        Worker worker = workerRepository.findWorkerByUuid(workerId).orElseThrow();
        worker.setOnShift(true);
        workerRepository.save(worker);

        EventLogDto log = new EventLogDto(
                worker.getUuid().toString(),
                "UPDATE",
                LocalDateTime.now(),
                "Worker status updated: " + worker.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
    }

    @Override
    public void dismissedWorkerByUuid(String uuid) {
        Worker worker = workerRepository.findWorkerByUuid(uuid).orElseThrow();

        worker.setDismissed(true);
        worker.setBrigade(null);
        worker.setOnShift(true);

        EventLogDto log = new EventLogDto(
                worker.getUuid().toString(),
                "DISMISSED",
                LocalDateTime.now(),
                "Worker dismissed: " + worker.toString()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

        workerRepository.save(worker);

    }

    @Autowired
    public void setWorkerRepository(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Autowired

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
