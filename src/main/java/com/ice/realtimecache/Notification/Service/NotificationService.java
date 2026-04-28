package com.ice.realtimecache.Notification.Service;

import com.ice.realtimecache.Notification.DTO.NotificationResponse;
import com.ice.realtimecache.Notification.Entity.Notification;
import com.ice.realtimecache.Notification.Repository.NotificationRepo;
import com.ice.realtimecache.Realtime.Service.SseService;
import com.ice.realtimecache.User.Entity.User;
import com.ice.realtimecache.User.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;
    private final SseService sseService;

    public void createNotification(Long userId, String title, String content)
    {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("not found user"));

        Notification notification = Notification.builder()
                .title(title)
                .content(content)
                .isRead(Boolean.FALSE)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        notificationRepo.save(notification);

        NotificationResponse response = toResponse(notification);

        sseService.sendToUser(userId, response);
    }

    private NotificationResponse toResponse(Notification notification)
    {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUser().getId())
                .title(notification.getTitle())
                .content(notification.getContent())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }

}
