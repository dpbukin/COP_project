package com.example.cop_rut.repositories;

import com.example.cop_rut.model.Client;
import org.apache.catalina.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    @Query("{'phoneNumber': ?0}")
    Optional<Client> findByPhoneNumber(String phoneNumber);
    @Query("{'email': ?0}")
    Optional<Client> findByEmail(String email);
    @Query("{'uuid': ?0}")
    Optional<Client> findClientByUuid(String uuid);
}
