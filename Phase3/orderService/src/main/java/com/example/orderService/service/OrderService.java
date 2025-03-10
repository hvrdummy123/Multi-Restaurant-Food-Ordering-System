package com.example.orderService.service;

import com.example.orderService.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order updateOrder(Long id, Order updatedOrder);
    void deleteOrder(Long id);
}
