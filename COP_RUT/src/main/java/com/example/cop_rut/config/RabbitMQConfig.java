package com.example.cop_rut.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitMQConfig{

    public static final String LOG_EXCHANGE = "logExchange";
    public static final String LOG_QUEUE = "logQueue";
    public static final String ROUTING_KEY = "log.key";

    @Bean
    public DirectExchange logExchange() {
        return new DirectExchange(LOG_EXCHANGE);
    }

    @Bean
    public Queue logQueue() {
        return QueueBuilder.durable(LOG_QUEUE).build();
    }

    @Bean
    public Binding logBinding(Queue logQueue, DirectExchange logExchange) {
        return BindingBuilder.bind(logQueue).to(logExchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
