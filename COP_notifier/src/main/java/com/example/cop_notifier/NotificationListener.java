package com.example.cop_notifier;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    public static final String NOTIFY_QUEUE = "notifyQueue";
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public NotificationListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = NOTIFY_QUEUE)
    public void listenForNotifications(String notificationMessage) {
        messagingTemplate.convertAndSend("/topic/notifications", notificationMessage);
    }
}
