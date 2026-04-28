package com.ice.realtimecache.Order.DTO;

import lombok.Data;

@Data
public class CreateOrderItemRequest {
    private Long productId;
    private Integer quantity;
}
