package com.se.orderservice.repository;

import com.se.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository {
    Order saveOrder(Order order);
    List<Order> findAllOrders();
}