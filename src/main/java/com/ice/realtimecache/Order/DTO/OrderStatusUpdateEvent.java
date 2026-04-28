package com.ice.realtimecache.Order.DTO;

import com.ice.realtimecache.Order.Entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateEvent {
    private Long orderId;

    private Long userId;

    private String userEmail;

    private OrderStatus oldStatus;

    private OrderStatus newStatus;
}
