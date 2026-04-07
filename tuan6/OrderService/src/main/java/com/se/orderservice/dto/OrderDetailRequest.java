package com.se.orderservice.dto;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private Long foodId;
    private Integer quantity;
}