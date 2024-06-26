package it.unisalento.pasproject.paymentservice.service;

import it.unisalento.pasproject.paymentservice.business.producer.MessageProducer;
import it.unisalento.pasproject.paymentservice.dto.NotificationMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationMessageHandler {
    private final MessageProducer messageProducer;

    @Value("${rabbitmq.exchange.notification.name}")
    private String notificationExchange;

    @Value("${rabbitmq.routing.notification.key}")
    private String notificationRoutingKey;

    @Autowired
    public NotificationMessageHandler(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public NotificationMessageDTO buildNotificationMessage(String receiver, String message, String subject, String attachment, String type, boolean email, boolean notification) {
        return NotificationMessageDTO.builder()
                .receiver(receiver)
                .message(message)
                .subject(subject)
                .attachment(attachment)
                .type(type)
                .email(email)
                .notification(notification)
                .build();
    }

    public void sendNotificationMessage(NotificationMessageDTO message) {
        messageProducer.sendMessage(message, notificationRoutingKey, notificationExchange);
    }
}
