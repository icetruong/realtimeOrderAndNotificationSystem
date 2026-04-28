package com.ice.realtimecache.Order.Service;

import com.ice.realtimecache.Order.DTO.CreateOrderRequest;
import com.ice.realtimecache.Order.DTO.OrderItemResponse;
import com.ice.realtimecache.Order.DTO.OrderResponse;
import com.ice.realtimecache.Order.Entity.Order;
import com.ice.realtimecache.Order.Entity.OrderItem;
import com.ice.realtimecache.Order.Entity.OrderStatus;
import com.ice.realtimecache.Order.Repository.OrderItemRepo;
import com.ice.realtimecache.Order.Repository.OrderRepo;
import com.ice.realtimecache.Product.Entity.Product;
import com.ice.realtimecache.Product.Repository.ProductRepo;
import com.ice.realtimecache.User.Entity.User;
import com.ice.realtimecache.User.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderItemRepo orderItemRepo;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Transactional
    public OrderResponse createOrder(String email,CreateOrderRequest request)
    {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found user"));
        Order order = Order.builder()
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        List<OrderItem> orderItem = request.getItems().stream().map(
            createOrderItemRequest -> {
                Product product = productRepo.findById(createOrderItemRequest.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("not found product"));

                BigDecimal price = product.getPrice();
                product.setStock(product.getStock() - createOrderItemRequest.getQuantity());
                productRepo.save(product);
                return OrderItem.builder()
                        .quantity(createOrderItemRequest.getQuantity())
                        .price(price)
                        .order(order)
                        .product(product)
                        .build();
            }
        ).toList();

        BigDecimal total = orderItem.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setItems(orderItem);
        order.setTotalAmount(total);
        orderRepo.save(order);
        orderItemRepo.saveAll(orderItem);

        return toResponse(order);
    }

    public OrderResponse getOrderById(Long id)
    {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found order"));

        return toResponse(order);
    }

    public List<OrderResponse> getAllOrder()
    {
        List<Order> orders = orderRepo.findAll();

        return orders.stream().map(
                this::toResponse
        ).toList();
    }

    public OrderResponse updateOrder(Long id, OrderStatus status)
    {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found order"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepo.save(order);
        return toResponse(order);
    }

    private OrderResponse toResponse(Order order)
    {
        List<OrderItemResponse> orderItemResponses = order.getItems().stream().map(
            orderItem -> OrderItemResponse.builder()
                    .productId(orderItem.getProduct().getId())
                    .productName(orderItem.getProduct().getName())
                    .quantity(orderItem.getQuantity())
                    .price(orderItem.getPrice())
                    .subTotal(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                    .build()
        ).toList();

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(orderItemResponses)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
