package com.ice.realtimecache.Notification.Repository;

import com.ice.realtimecache.Notification.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}
