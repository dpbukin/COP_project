//package com.example.cop_rut.service.impl;
//
//import com.example.cop_rut.config.RabbitMQConfig;
//import com.example.cop_rut.dtos.EventLogDto;
//import com.example.cop_rut.dtos.base.BrigadeDto;
////import com.example.cop_rut.model.Brigade;
//import com.example.cop_rut.repositories.BrigadeRepository;
//import com.example.cop_rut.service.BrigadeService;
//import org.modelmapper.ModelMapper;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//public class BrigadeServiceImpl implements BrigadeService {
//
//    private final RabbitTemplate rabbitTemplate;
//    private BrigadeRepository brigadeRepository;
//    private ModelMapper modelMapper;
//    private NotificationService notificationService;
//
//    public BrigadeServiceImpl(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Override
//    public void save(Brigade brigade) {
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "SAVE",
//                LocalDateTime.now(),
//                "Brigade saved: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//
//    }
//
//    @Override
//    public BrigadeDto add(BrigadeDto brigadeDto) {
//        Brigade brigade = modelMapper.map(brigadeDto, Brigade.class);
//        brigade.setUuid(String.valueOf(UUID.randomUUID()));
//        brigade.setFreeness(true);
//        brigade.setCreateDate(LocalDateTime.now());
//        brigade.setUpdateDate(LocalDateTime.now());
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "CREATE",
//                LocalDateTime.now(),
//                "Brigade created: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//
//
//        return brigadeDto;
//    }
//
//    @Override
//    public BrigadeDto getBrigadeByUuid(String uuid) {
//        return modelMapper.map(brigadeRepository.findBrigadeByUuid(uuid), BrigadeDto.class);
//    }
//
//    @Override
//    public List<BrigadeDto> getAllBrigades() {
//        return brigadeRepository.findAll().stream()
//                .map(brigade -> modelMapper.map(brigade, BrigadeDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BrigadeDto> getFreeBrigades() {
//        return brigadeRepository.findBrigadeByFreeness(true).stream()
//                .map(brigade -> modelMapper.map(brigade, BrigadeDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public BrigadeDto updateBrigade(BrigadeDto brigadeDto) {
//        Brigade brigade = brigadeRepository.findBrigadeByUuid(brigadeDto.getUuid());
//
//        if (brigadeDto.getPhoneNumber() != null) {
//            brigade.setPhoneNumber(brigadeDto.getPhoneNumber());
//        }
//
//        if (brigadeDto.getWorkersId() != null) {
//            brigade.setWorkersId(brigadeDto.getWorkersId());
//        }
//
//        if (brigadeDto.getFreeness() != null) {
//            brigade.setFreeness(brigadeDto.getFreeness());
//        }
//
//        if (brigadeDto.getOrder() != null) {
//            brigade.setOrder(brigadeDto.getOrder());
//        }
//
//        brigade.setUpdateDate(LocalDateTime.now());
//
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "UPDATE",
//                LocalDateTime.now(),
//                "Brigade updated: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//
//        return modelMapper.map(brigade, BrigadeDto.class);
//    }
//
//    @Override
//    public void addWorkerInBrigade(String brigadeId, String workerId) {
//        Brigade brigade = brigadeRepository.findBrigadeByUuid(brigadeId);
//        List<String> workers = brigade.getWorkersId();
//        workers.add(workerId);
//        brigade.setWorkersId(workers);
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "UPDATE",
//                LocalDateTime.now(),
//                "Brigade updated, worker add in brigade: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//
//    }
//
//    @Override
//    public void removeWorkerFromBrigade(String brigadeId, String workerId) {
//        Brigade brigade = brigadeRepository.findBrigadeByUuid(brigadeId);
//        List<String> workers = brigade.getWorkersId();
//        for(String worker_id : workers){
//            if(worker_id.equals(workerId)){
//                workers.remove(worker_id);
//            }
//        }
//        brigade.setWorkersId(workers);
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "UPDATE",
//                LocalDateTime.now(),
//                "Brigade updated, worker remove from brigade: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//    }
//
//    @Override
//    public void updateBrigadeStatusAndOrder(String brigadeId, Boolean status, String order) {
//        Brigade brigade = brigadeRepository.findBrigadeByUuid(brigadeId);
//        brigade.setFreeness(status);
//        brigade.setOrder(order);
//
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "UPDATE",
//                LocalDateTime.now(),
//                "Brigade status updated, order add in brigade: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//
//    }
//
//    @Override
//    public void removeOrderFromBrigade(String brigadeId) {
//        Brigade brigade = brigadeRepository.findBrigadeByUuid(brigadeId);
//        brigade.setFreeness(true);
//        brigade.setOrder(null);
//
//        brigadeRepository.save(brigade);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "UPDATE",
//                LocalDateTime.now(),
//                "Brigade status updated, order delete from brigade: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//    }
//
//    @Override
//    public void removeBrigadeByUuid(String brigadeId) {
//        Brigade brigade = brigadeRepository.findBrigadeByUuid(brigadeId);
//        brigadeRepository.deleteById(brigadeId);
//
//        EventLogDto log = new EventLogDto(
//                brigade.getUuid().toString(),
//                "DELETE",
//                LocalDateTime.now(),
//                "Brigade deleted: " + brigade.toString()
//        );
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
//    }
//
//
//    @Autowired
//    public void setBrigadeRepository(BrigadeRepository brigadeRepository) {
//        this.brigadeRepository = brigadeRepository;
//    }
//
//    @Autowired
//    public void setModelMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    @Autowired
//    public void setNotificationService(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//}
