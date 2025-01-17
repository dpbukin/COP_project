package com.example.cop_rut.repositories;

import com.example.cop_rut.model.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends MongoRepository<Worker, String> {
    @Query("{'brigade': ?0}")
    List<Worker> findWorkersByBrigade(String uuid);
    @Query("{'uuid': ?0}")
    Optional<Worker> findWorkerByUuid(String uuid);
    List<Worker> findWorkersByIsOnShift(boolean isOnShift);


}
