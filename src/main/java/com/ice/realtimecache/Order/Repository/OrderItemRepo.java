package com.ice.realtimecache.Order.Repository;

import com.ice.realtimecache.Order.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
