package com.example.orderService.controller;

import com.example.orderService.dto.MenuItemDTO;
import com.example.orderService.dto.UserDTO;
import com.example.orderService.entity.Order;
import com.example.orderService.feign.RestaurantServiceClient;
import com.example.orderService.feign.UserServiceClient;
import com.example.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserServiceClient userServiceClient;
    private final RestaurantServiceClient restaurantServiceClient;

    @Autowired
    public OrderController(OrderService orderService,
                           UserServiceClient userServiceClient,
                           RestaurantServiceClient restaurantServiceClient) {
        this.orderService = orderService;
        this.userServiceClient = userServiceClient;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order,
                                         @RequestHeader("userId") Long userId,
                                         @RequestHeader("restaurantId") Long restaurantId) {
        try {
            // Validate user
            UserDTO user = userServiceClient.getUserById(userId);
            if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Collections.singletonMap("error", "Only users can place orders."));
            }

            // Validate restaurant menu
            List<MenuItemDTO> menuItems = restaurantServiceClient.getMenuItems(restaurantId);
            if (menuItems == null || menuItems.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "Restaurant menu is unavailable."));
            }

            // Validate that all ordered items are in the menu
            Set<String> menuItemNames = new HashSet<>();
            for (MenuItemDTO item : menuItems) {
                menuItemNames.add(item.getName().toLowerCase().trim());
            }

            // ✅ Parse comma-separated string to validate
            String[] orderedItems = order.getItems().split(",");
            for (String item : orderedItems) {
                if (!menuItemNames.contains(item.toLowerCase().trim())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(Collections.singletonMap("error", "Item '" + item.trim() + "' is not available in the restaurant's menu."));
                }
            }

            // Set the userId and restaurantId into the order object
            order.setUserId(userId);
            order.setRestaurantId(restaurantId);

            // Save the order
            Order createdOrder = orderService.createOrder(order);
            return ResponseEntity.ok(createdOrder);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an order
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        try {
            Order order = orderService.updateOrder(id, updatedOrder);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
