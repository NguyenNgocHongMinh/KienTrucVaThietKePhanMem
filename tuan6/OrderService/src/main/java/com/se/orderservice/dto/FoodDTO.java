package com.se.orderservice.dto;

import lombok.Data;

@Data
public class FoodDTO {
    private Long id;
    private String name;
    private Double price;
    private boolean isAvailable;
}
