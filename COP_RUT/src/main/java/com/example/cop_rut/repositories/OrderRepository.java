package com.example.cop_rut.repositories;

import com.example.cop_rut.model.Order;
import com.example.cop_rut.model.enam.order.CleaningType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{'client': ?0}")
    List<Order> findOrderByClient(String clientUuid);
    @Query("{'uuid': ?0}")
    Optional<Order> findOrderByUuid(String uuid);
    @Query("{'archived': true}")
    List<Order> findOrderByArchivedStatus(boolean archived);
    @Query("{'executionDate': ?0}")
    List<Order> findOrderByExecutionDate(LocalDateTime executionDate);
    @Query("{'orderNumber': ?0}")
    Optional<Order> findOrderByOrderNumber(String orderNumber);
    @Query("{'cleaningType': ?0}")
    List<Order> findOrdersByCleaningType(CleaningType cleaningType);

    @Query("{'brigade': ?0}")
    List<Order> findOrdersByBrigadeId(String brigadeId);

}
