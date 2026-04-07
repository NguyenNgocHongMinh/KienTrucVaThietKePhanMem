package com.se.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Đổi từ Long sang String

    private long foodId; // Đổi sang String (UUID của Food)
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
