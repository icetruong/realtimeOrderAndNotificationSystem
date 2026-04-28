package com.ice.realtimecache.Notification.Controller;

import com.ice.realtimecache.Common.Config.KafkaTopics;
import com.ice.realtimecache.Notification.Service.EmailService;
import com.ice.realtimecache.Notification.Service.NotificationService;
import com.ice.realtimecache.Order.DTO.OrderCreatedEvent;
import com.ice.realtimecache.Order.DTO.OrderStatusUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final EmailService emailService;

    @KafkaListener(topics = KafkaTopics.ORDER_CREATED, groupId = "notification-group")
    public void handleOrderCreate(OrderCreatedEvent event) {
        String title = "Tao don hang thanh cong";
        String content = "Don hang #" + event.getOrderId() + " da duoc tao thanh cong";

        notificationService.createNotification(
                event.getUserId(),
                title,
                content
        );

        emailService.sendOrderCreatedEmail(
                event.getUserEmail(),
                event.getOrderId()
        );
    }

    @KafkaListener(topics = KafkaTopics.ORDER_STATUS_UPDATED, groupId = "notification-group")
    public void handleOrderStatus(OrderStatusUpdateEvent event) {
        String title = "Cap nhat trang thai don hang";
        String content = "Don hang #" + event.getOrderId()
                + " da chuyen tu "
                + event.getOldStatus()
                + " sang "
                + event.getNewStatus();

        notificationService.createNotification(
                event.getUserId(),
                title,
                content
        );

        emailService.sendOrderStatusUpdatedEmail(
                event.getUserEmail(),
                event.getOrderId(),
                event.getNewStatus().name()
        );
    }
}
