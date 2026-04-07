package com.se.orderservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private String userId; // Đổi sang String
    private List<ItemRequest> items;
    private String paymentMethod;

    @Data
    public static class ItemRequest {
        private String foodId; // Đổi sang String
        private Integer quantity;
    }
}
