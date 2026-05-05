package iuh.fit.productpu.service;

import iuh.fit.productpu.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/*
 * @ (#) f.java     1.0    05-May-26
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Lấy 1 sản phẩm
    public Product getStock(String productId) {
        return (Product) redisTemplate.opsForHash().get("products", productId);
    }

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        return redisTemplate.opsForHash().values("products")
                .stream()
                .map(obj -> (Product) obj)
                .toList();
    }

}