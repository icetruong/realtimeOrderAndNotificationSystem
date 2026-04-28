package com.ice.realtimecache.Order.DTO;

import com.ice.realtimecache.Order.Entity.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private List<OrderItemResponse> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
