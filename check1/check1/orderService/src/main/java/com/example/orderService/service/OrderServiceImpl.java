package com.example.orderService.service;

import com.example.orderService.dto.RestaurantDTO;
import com.example.orderService.dto.UserDTO;
import com.example.orderService.entity.Order;
import com.example.orderService.feign.RestaurantServiceClient;
import com.example.orderService.feign.UserServiceClient;
import com.example.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final RestaurantServiceClient restaurantServiceClient;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserServiceClient userServiceClient,
                            RestaurantServiceClient restaurantServiceClient) {
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    @Override
    public Order createOrder(Order order) {
        // Fetch user details from UserService
        UserDTO user = userServiceClient.getUserById(order.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + order.getUserId());
        }

        // Fetch restaurant details from RestaurantService
        RestaurantDTO restaurant = restaurantServiceClient.getRestaurantById(order.getRestaurantId());
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found with ID: " + order.getRestaurantId());
        }

        // Save the order if validations pass
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setUserId(updatedOrder.getUserId());
                    existingOrder.setRestaurantId(updatedOrder.getRestaurantId());
                    existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
                    existingOrder.setStatus(updatedOrder.getStatus());
                    return orderRepository.save(existingOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
