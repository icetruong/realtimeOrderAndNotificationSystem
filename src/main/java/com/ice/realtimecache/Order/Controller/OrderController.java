package com.ice.realtimecache.Order.Controller;

import com.ice.realtimecache.Order.DTO.CreateOrderRequest;
import com.ice.realtimecache.Order.DTO.OrderResponse;
import com.ice.realtimecache.Order.Entity.OrderStatus;
import com.ice.realtimecache.Order.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request, Authentication authentication)
    {
        return orderService.createOrder(authentication.getName(), request);
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable Long id)
    {
        return orderService.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<OrderResponse> getAllOrders()
    {
        return orderService.getAllOrder();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/orders/{id}/status")
    public OrderResponse updateStatus(@PathVariable Long id, @RequestBody OrderStatus status)
    {
        return orderService.updateOrder(id,status);
    }

}
