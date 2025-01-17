package com.example.cop_consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class LogConsumer {
    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;
    @Autowired
    public LogConsumer(LogRepository logRepository, ObjectMapper objectMapper) {
        this.logRepository = logRepository;
        this.objectMapper = objectMapper;
    }
    @RabbitListener(queues = RabbitMQConfig.LOG_QUEUE)
    public void consumeMessage(String message) {
        try {
            EventLog eventLog = objectMapper.readValue(message, EventLog.class);
            logRepository.save(eventLog);
            System.out.println("Log saved: " + eventLog);
        } catch (Exception e) {
            System.err.println("Failed to process message: " + e.getMessage());
        }
    }
}