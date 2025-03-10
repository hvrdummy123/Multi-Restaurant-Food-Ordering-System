package com.example.orderService.service;

import com.example.orderService.dto.UserDTO;
import com.example.orderService.dto.MenuItemDTO;
import com.example.orderService.entity.Order;
import com.example.orderService.feign.RestaurantServiceClient;
import com.example.orderService.feign.UserServiceClient;
import com.example.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final RestaurantServiceClient restaurantServiceClient;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserServiceClient userServiceClient,
                            RestaurantServiceClient restaurantServiceClient) {
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    @Override
    public Order createOrder(Order order) {
        // Fetch user details from UserService
        UserDTO user = userServiceClient.getUserById(order.getUserId());
        if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("Only users with role 'USER' can place orders.");
        }

        // Fetch menu items from RestaurantService
        List<MenuItemDTO> menuItems = restaurantServiceClient.getMenuItems(order.getRestaurantId());
        if (menuItems == null || menuItems.isEmpty()) {
            throw new RuntimeException("Restaurant menu is unavailable.");
        }

        // Normalize menu item names for case-insensitive comparison
        Set<String> availableMenuItems = menuItems.stream()
                .map(item -> item.getName().toLowerCase().trim())
                .collect(Collectors.toSet());

        // Validate that ordered items exist in the restaurant's menu
        for (String item : order.getItems().split(",")) {
            if (!availableMenuItems.contains(item.toLowerCase().trim())) {
                throw new RuntimeException("Menu item '" + item.trim() + "' is not available in this restaurant.");
            }
        }

        // Ensure total price is stored as BigDecimal
        order.setTotalPrice(BigDecimal.valueOf(order.getTotalPrice().doubleValue()));

        // Save and return the order
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id)
                .or(() -> {
                    throw new RuntimeException("Order not found with ID: " + id);
                });
    }

    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        // Fetch menu items for the given restaurant
        List<MenuItemDTO> menuItems = restaurantServiceClient.getMenuItems(updatedOrder.getRestaurantId());
        if (menuItems == null || menuItems.isEmpty()) {
            throw new RuntimeException("Restaurant menu is unavailable.");
        }

        // Normalize menu item names
        Set<String> availableMenuItems = menuItems.stream()
                .map(item -> item.getName().toLowerCase().trim())
                .collect(Collectors.toSet());

        // Validate the item exists in the restaurant's menu
        for (String item : updatedOrder.getItems().split(",")) {
            if (!availableMenuItems.contains(item.toLowerCase().trim())) {
                throw new RuntimeException("Invalid item. '" + item.trim() + "' is not available at restaurant ID " + updatedOrder.getRestaurantId());
            }
        }

        // Update order details
        existingOrder.setItems(updatedOrder.getItems());
        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
        existingOrder.setStatus(updatedOrder.getStatus());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
