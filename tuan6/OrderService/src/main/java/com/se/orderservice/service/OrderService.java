package com.se.orderservice.service;

import com.se.orderservice.dto.FoodDTO;
import com.se.orderservice.dto.OrderRequest;
import com.se.orderservice.dto.UserDTO;
import com.se.orderservice.entity.Order;
import com.se.orderservice.entity.OrderItem;
import com.se.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    // Giả sử các service khác đang chạy ở port 8081 và 8082
    private final String USER_SERVICE_URL = "http://localhost:8081/api/users/";
    private final String FOOD_SERVICE_URL = "http://localhost:8082/api/foods/";

    @Transactional
    public Order createOrder(OrderRequest request) {
        // 1. Gọi User Service để validate
        ResponseEntity<UserDTO> userResponse = restTemplate.getForEntity(
                USER_SERVICE_URL + request.getUserId(), UserDTO.class);

        if (!userResponse.getStatusCode().is2xxSuccessful() || userResponse.getBody() == null) {
            throw new RuntimeException("User không hợp lệ!");
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setStatus("SUCCESS"); // Đơn giản hóa trạng thái

        double totalAmount = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        // 2. Gọi Food Service cho từng món ăn
        for (OrderRequest.ItemRequest itemReq : request.getItems()) {
            ResponseEntity<FoodDTO> foodResponse = restTemplate.getForEntity(
                    FOOD_SERVICE_URL + itemReq.getFoodId(), FoodDTO.class);

            FoodDTO foodDTO = foodResponse.getBody();
            if (foodDTO == null || !foodDTO.isAvailable()) {
                throw new RuntimeException("Món ăn ID " + itemReq.getFoodId() + " không khả dụng!");
            }

            OrderItem item = new OrderItem();
            item.setFoodId(foodDTO.getId());
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(foodDTO.getPrice());
            item.setOrder(order);

            orderItems.add(item);
            totalAmount += (foodDTO.getPrice() * itemReq.getQuantity());
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        // 3. Lưu vào database thông qua Repository Impl
        Order savedOrder = orderRepository.saveOrder(order);

        // 4. Console log thông báo
        System.out.println("Gửi thông báo: Đặt hàng thành công cho User ID " + savedOrder.getUserId());

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }
}