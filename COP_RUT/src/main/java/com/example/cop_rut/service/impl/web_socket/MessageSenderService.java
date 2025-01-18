package com.example.cop_rut.service.impl.web_socket;

import com.example.cop_rut.config.RabbitMQConfig;
import com.example.cop_rut.dtos.EventLogDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {
    private RabbitTemplate rabbitTemplate;

    public void sendLogMessage(EventLogDto eventLogDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE, RabbitMQConfig.LOG_ROUTING_KEY, eventLogDto);
    }
    public void sendNotificationMessage(String notificationMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFY_EXCHANGE, RabbitMQConfig.NOTIFY_ROUTING_KEY, notificationMessage);
    }
    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
