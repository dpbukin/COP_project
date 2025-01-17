package com.example.cop_consumer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String LOG_QUEUE = "logQueue";
    @Bean
    public Queue logQueue() {
        return QueueBuilder.durable(LOG_QUEUE).build();
    }
}
