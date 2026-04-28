package com.ice.realtimecache.Order.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<CreateOrderItemRequest> items;
}
