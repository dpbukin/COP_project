package com.example.cop_rut.service.impl;

import com.example.cop_rut.config.RabbitMQConfig;
import com.example.cop_rut.dtos.EventLogDto;
import com.example.cop_rut.dtos.base.BrigadeDto;
import com.example.cop_rut.service.BrigadeService;
import cop_grpc.Brigade.*;
import cop_grpc.BrigadeServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.cop_rut_contracts.exception.InternalServerErrorException;
@Service
public class BrigadeServiceImpl implements BrigadeService {

    @GrpcClient("cop_rut")
    private BrigadeServiceGrpc.BrigadeServiceBlockingStub brigadeServiceStub;
    private RabbitTemplate rabbitTemplate;

    @Override
    public BrigadeDto add(BrigadeDto brigadeDto) {
        try {
            AddBrigadeRequest request = AddBrigadeRequest.newBuilder()
                    .setPhoneNumber(brigadeDto.getPhoneNumber())
                    .addAllWorkersId(brigadeDto.getWorkersId())
                    .build();

            AddBrigadeResponse response = brigadeServiceStub.addBrigade(request);

            EventLogDto log = new EventLogDto(
                    response.getUuid(),
                    "CREATE",
                    LocalDateTime.now(),
                    "Brigade created: " + brigadeDto.toString()
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

            brigadeDto.setUuid(response.getUuid());
            return brigadeDto;

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    @Override
    public BrigadeDto getBrigadeByUuid(String brigadeId) {
        try {
            FreeBrigadeRequest request = FreeBrigadeRequest.newBuilder()
                    .setBrigadeUuid(brigadeId)
                    .build();

            FreeBrigadeResponse response = brigadeServiceStub.freeBrigade(request);

            EventLogDto log = new EventLogDto(
                    brigadeId,
                    "FETCH",
                    LocalDateTime.now(),
                    "Brigade fetched: " + response.getMessage()
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

            BrigadeDto brigadeDto = new BrigadeDto();
            brigadeDto.setUuid(brigadeId);

            return brigadeDto;

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    @Override
    public List<BrigadeDto> getAllBrigades() {
        try {
            BrigadeListResponse response = brigadeServiceStub.getAllBrigades(Empty.newBuilder().build());

            EventLogDto log = new EventLogDto(
                    "ALL",
                    "FETCH",
                    LocalDateTime.now(),
                    "All brigades fetched"
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

            return response.getBrigadesList().stream()
                    .map(brigadeResponse -> {
                        BrigadeDto brigadeDto = new BrigadeDto();
                        brigadeDto.setUuid(brigadeResponse.getUuid());
                        brigadeDto.setPhoneNumber(brigadeResponse.getPhoneNumber());
                        brigadeDto.setWorkersId(brigadeResponse.getWorkersIdList());
                        brigadeDto.setFreeness(brigadeResponse.getFreeness());
                        brigadeDto.setOrder(brigadeResponse.getOrder());
                        return brigadeDto;
                    })
                    .collect(Collectors.toList());

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    @Override
    public String freeBrigade(String brigadeUuid) {
        try {
            FreeBrigadeRequest request = FreeBrigadeRequest.newBuilder()
                    .setBrigadeUuid(brigadeUuid)
                    .build();

            FreeBrigadeResponse response = brigadeServiceStub.freeBrigade(request);

            EventLogDto log = new EventLogDto(
                    "BRIGADE",
                    "FREE",
                    LocalDateTime.now(),
                    "Brigade " + brigadeUuid + " freed"
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

            return response.getMessage();

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    @Override
    public BrigadeDto updateBrigade(BrigadeDto brigadeDto) {
        try {
            UpdateBrigadeRequest request = UpdateBrigadeRequest.newBuilder()
                    .setUuid(brigadeDto.getUuid())
                    .setPhoneNumber(brigadeDto.getPhoneNumber())
                    .addAllWorkersId(brigadeDto.getWorkersId())
                    .build();

            BrigadeResponse response = brigadeServiceStub.updateBrigade(request);

            EventLogDto log = new EventLogDto(
                    brigadeDto.getUuid(),
                    "UPDATE",
                    LocalDateTime.now(),
                    "Brigade updated: " + brigadeDto.toString()
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

            brigadeDto.setPhoneNumber(response.getPhoneNumber());
            brigadeDto.setWorkersId(response.getWorkersIdList());
            brigadeDto.setFreeness(response.getFreeness());
            brigadeDto.setOrder(response.getOrder());
            return brigadeDto;

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    @Override
    public void removeBrigadeByUuid(String brigadeId) {
        try {
            DeleteBrigadeRequest request = DeleteBrigadeRequest.newBuilder()
                    .setBrigadeUuid(brigadeId)
                    .build();
            brigadeServiceStub.deleteBrigade(request);

            EventLogDto log = new EventLogDto(
                    brigadeId,
                    "DELETE",
                    LocalDateTime.now(),
                    "Brigade deleted: " + brigadeId
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    @Override
    public String assignBrigadeToOrder(String orderId) {
        try {
            AssignBrigadeRequest request = AssignBrigadeRequest.newBuilder()
                    .setOrderId(orderId)
                    .build();

            AssignBrigadeResponse response = brigadeServiceStub.assignBrigadeToOrder(request);

            EventLogDto log = new EventLogDto(
                    response.getUuid(),
                    "ASSIGN",
                    LocalDateTime.now(),
                    "Brigade assigned to order: " + orderId
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, log);
            return response.getUuid();

        } catch (StatusRuntimeException e) {
            handleGrpcError(e);
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }

    private void handleGrpcError(StatusRuntimeException e) {
        if (e.getStatus().getCode() == Status.Code.UNAVAILABLE || e.getStatus().getCode() == Status.Code.INTERNAL) {
            throw new InternalServerErrorException("gRPC service is down or returned an error");
        }
    }
    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
