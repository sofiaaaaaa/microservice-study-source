package com.example.demo.service;


import com.example.demo.dto.OrderDto;
import com.example.demo.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
