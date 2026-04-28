package com.ice.realtimecache.Notification.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Boolean isRead;

    private LocalDateTime createdAt;
}
