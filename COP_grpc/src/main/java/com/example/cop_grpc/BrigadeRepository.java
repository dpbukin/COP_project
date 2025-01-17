package com.example.cop_grpc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrigadeRepository extends MongoRepository<Brigade, String> {
    List<Brigade> findByFreeness(Boolean freeness);
    @Query("{ 'uuid': ?0 }")
    Optional<Brigade> findByUuid(String uuid);;
    void deleteById(String uuid);
}
