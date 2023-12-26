package com.devluis.shopping.orderservice.repository;

import com.devluis.shopping.orderservice.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
