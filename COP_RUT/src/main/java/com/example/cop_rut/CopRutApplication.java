package com.example.cop_rut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class CopRutApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopRutApplication.class, args);

    }

}
