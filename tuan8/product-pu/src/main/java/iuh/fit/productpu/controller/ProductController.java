package iuh.fit.productpu.controller;
import iuh.fit.productpu.entity.Product;

/*
 * @ (#) f.java     1.0    05-May-26
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */


import iuh.fit.productpu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService inventoryService;

    // Lấy danh sách tất cả sản phẩm (trả về full thông tin)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    // Lấy 1 sản phẩm theo ID (trả về full thông tin)
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product product = inventoryService.getStock(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}