package com.example.orderService.service;

import com.example.orderService.dto.UserDTO;
import com.example.orderService.dto.MenuItemDTO;
import com.example.orderService.entity.Order;
import com.example.orderService.feign.RestaurantServiceClient;
import com.example.orderService.feign.UserServiceClient;
import com.example.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public OrderServiceImpl(OrderRepository orderRepository, UserServiceClient userServiceClient, RestaurantServiceClient restaurantServiceClient) {
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    @Override
    public Order createOrder(Order order) {
        // Fetch user details from UserService
        UserDTO user = userServiceClient.getUserById(order.getUserId());
        if (!"USER".equals(user.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only users with role 'USER' can place orders.");
        }

        // Fetch menu items from RestaurantService
//        List<MenuItemDTO> menuItems = restaurantServiceClient.getMenuItemsByRestaurant(order.getRestaurantId());
//        Set<String> availableMenuItems = menuItems.stream().map(MenuItemDTO::getName).collect(Collectors.toSet());
        List<MenuItemDTO> menuItems = restaurantServiceClient.getMenuItems(order.getRestaurantId());
        Set<String> availableMenuItems = menuItems.stream().map(MenuItemDTO::getName).collect(Collectors.toSet());


        // Validate menu items exist in the restaurant
        for (String item : order.getItems().split(",")) {
            if (!availableMenuItems.contains(item.trim())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Menu item '" + item.trim() + "' is not available in this restaurant.");
            }
        }

        // Ensure total price is stored as BigDecimal
        order.setTotalPrice(BigDecimal.valueOf(order.getTotalPrice().doubleValue()));

        // Save the order
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
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Fetch menu items for the given restaurant
        List<MenuItemDTO> menuItems = restaurantServiceClient.getMenuItems(updatedOrder.getRestaurantId());
        Set<String> availableMenuItems = menuItems.stream().map(MenuItemDTO::getName).collect(Collectors.toSet());

        // Validate the item exists in the restaurant's menu
        if (!availableMenuItems.contains(updatedOrder.getItems())) {
            throw new RuntimeException("Invalid item. '" + updatedOrder.getItems() + "' is not available at restaurant ID " + updatedOrder.getRestaurantId());
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
