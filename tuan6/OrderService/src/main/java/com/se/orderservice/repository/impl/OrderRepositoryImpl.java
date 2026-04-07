package com.se.orderservice.repository.impl;

import com.se.orderservice.entity.Order;
import com.se.orderservice.repository.OrderRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    // Dùng EntityManager để thao tác Database thủ công thay vì JpaRepository có sẵn
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
            return order;
        } else {
            return entityManager.merge(order);
        }
    }

    @Override
    public List<Order> findAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
