package com.se.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Đổi từ Long sang String, Spring Boot 3 sẽ tự động generate UUID

    private String userId; // Đổi sang String (UUID của User)
    private Double totalAmount;
    private String paymentMethod;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();
}
