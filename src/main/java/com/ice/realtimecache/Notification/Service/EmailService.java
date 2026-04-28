package com.ice.realtimecache.Notification.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    public void sendOrderCreatedEmail(String toEmail, Long orderId) {
        log.info("FAKE EMAIL to {}: Don hang #{} da duoc tao thanh cong", toEmail, orderId);
    }

    public void sendOrderStatusUpdatedEmail(String toEmail, Long orderId, String status) {
        log.info("FAKE EMAIL to {}: Don hang #{} da chuyen sang trang thai {}", toEmail, orderId, status);
    }
}
