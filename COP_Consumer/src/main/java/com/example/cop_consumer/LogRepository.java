package com.example.cop_consumer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<EventLog, String> {
}