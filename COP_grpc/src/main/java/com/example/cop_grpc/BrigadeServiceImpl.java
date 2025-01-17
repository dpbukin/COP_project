package com.example.cop_grpc;

import cop_grpc.Brigade.*;

import cop_grpc.BrigadeServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
public class BrigadeServiceImpl extends BrigadeServiceGrpc.BrigadeServiceImplBase {

    private BrigadeRepository brigadeRepository;
    @Override
    public void addBrigade(AddBrigadeRequest request, StreamObserver<AddBrigadeResponse> responseObserver) {
        Brigade brigade = new Brigade();
        brigade.setUuid(UUID.randomUUID().toString());
        brigade.setPhoneNumber(request.getPhoneNumber());
        brigade.setWorkersId(request.getWorkersIdList());
        brigade.setFreeness(true);
        brigade.setOrder(null);

        brigade = brigadeRepository.save(brigade);

        AddBrigadeResponse response = AddBrigadeResponse.newBuilder()
                .setUuid(brigade.getUuid())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void deleteBrigade(DeleteBrigadeRequest request, StreamObserver<BrigadeResponse> responseObserver) {
        Brigade brigade = brigadeRepository.findByUuid(request.getBrigadeUuid()).orElse(null);
        if (brigade == null) {
            responseObserver.onError(new RuntimeException("Бригада не найдена"));
            return;
        }

        brigadeRepository.delete(brigade);

        BrigadeResponse response = BrigadeResponse.newBuilder()
                .setUuid(brigade.getUuid())
                .setPhoneNumber(brigade.getPhoneNumber())
                .addAllWorkersId(brigade.getWorkersId())
                .setFreeness(brigade.getFreeness())
                .setOrder(brigade.getOrder() != null ? brigade.getOrder() : "")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateBrigade(UpdateBrigadeRequest request, StreamObserver<BrigadeResponse> responseObserver) {
        Brigade brigade = brigadeRepository.findByUuid(request.getUuid()).orElse(null);
        if (brigade == null) {
            responseObserver.onError(new RuntimeException("Бригада не найдена"));
            return;
        }

        brigade.setPhoneNumber(request.getPhoneNumber());
        brigade.setWorkersId(request.getWorkersIdList());

        brigade = brigadeRepository.save(brigade);

        BrigadeResponse response = BrigadeResponse.newBuilder()
                .setUuid(brigade.getUuid())
                .setPhoneNumber(brigade.getPhoneNumber())
                .addAllWorkersId(brigade.getWorkersId())
                .setFreeness(brigade.getFreeness())
                .setOrder(brigade.getOrder() != null ? brigade.getOrder() : "")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void freeBrigade(FreeBrigadeRequest request, StreamObserver<FreeBrigadeResponse> responseObserver) {
        Brigade brigade = brigadeRepository.findByUuid(request.getBrigadeUuid()).orElse(null);
        if (brigade == null) {
            responseObserver.onError(new RuntimeException("Бригада не найдена"));
            return;
        }

        brigade.setFreeness(true);
        brigade.setOrder(null);
        brigadeRepository.save(brigade);

        FreeBrigadeResponse response = FreeBrigadeResponse.newBuilder()
                .setMessage("Бригада с UUID " + request.getBrigadeUuid() + " успешно освобождена")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void assignBrigadeToOrder(AssignBrigadeRequest request, StreamObserver<AssignBrigadeResponse> responseObserver) {
        List<Brigade> freeBrigades = brigadeRepository.findByFreeness(true);
        if (freeBrigades.isEmpty()) {
            responseObserver.onError(new RuntimeException("Нет свободных бригад"));
            return;
        }

        Brigade brigade = freeBrigades.get(0);
        brigade.setFreeness(false);
        brigade.setOrder(request.getOrderId());
        brigade = brigadeRepository.save(brigade);

        AssignBrigadeResponse response = AssignBrigadeResponse.newBuilder()
                .setUuid(brigade.getUuid())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllBrigades(cop_grpc.Brigade.Empty request, StreamObserver<BrigadeListResponse> responseObserver) {
        List<Brigade> brigades = brigadeRepository.findAll();
        List<BrigadeResponse> responseList = brigades.stream()
                .map(brigade -> BrigadeResponse.newBuilder()
                        .setUuid(brigade.getUuid())
                        .setPhoneNumber(brigade.getPhoneNumber())
                        .addAllWorkersId(brigade.getWorkersId())
                        .setFreeness(brigade.getFreeness())
                        .setOrder(brigade.getOrder() != null ? brigade.getOrder() : "")
                        .build())
                .collect(Collectors.toList());

        BrigadeListResponse response = BrigadeListResponse.newBuilder()
                .addAllBrigades(responseList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Autowired
    public void setBrigadeRepository(BrigadeRepository brigadeRepository) {
        this.brigadeRepository = brigadeRepository;
    }
}
