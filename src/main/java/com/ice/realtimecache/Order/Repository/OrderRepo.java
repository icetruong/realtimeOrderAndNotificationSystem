package com.ice.realtimecache.Order.Repository;

import com.ice.realtimecache.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
