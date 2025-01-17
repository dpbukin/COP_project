//package com.example.cop_rut.repositories;
//
//import com.example.cop_rut.model.Brigade;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface BrigadeRepository extends MongoRepository<Brigade, String> {
//    @Query("{'freeness': ?0}")
//    List<Brigade> findBrigadeByFreeness(boolean freeness);
//    @Query("{'uuid': ?0}")
//    Brigade findBrigadeByUuid(String uuid);
//
//}
