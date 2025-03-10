package com.example.controller;

import com.example.dto.DeliveryResponseDTO;
import com.example.dto.OrderDTO;
import com.example.entity.Delivery;
import com.example.service.DeliveryService;

import com.example.service.OrderServiceClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
@Tag(name = "Deliveries", description = "APIs for managing deliveries")
public class DeliveryController {
    @Autowired
    public DeliveryService deliveryService;
    @Autowired
    private OrderServiceClient orderServiceClient;
    @GetMapping
    @Operation(summary = "Get all deliveries", description = "Fetches the list of all deliveries with their corresponding orders")
    public ResponseEntity<List<DeliveryResponseDTO>> getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();

        List<DeliveryResponseDTO> responseList = deliveries.stream().map(delivery -> {
            OrderDTO order = fetchOrderDetails(delivery.getOrderId());
            return new DeliveryResponseDTO(delivery, order);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get delivery by ID", description = "Fetches a delivery by its unique ID along with order details")
    public ResponseEntity<?> getDeliveryById(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID must not be null");
        }
        Optional<Delivery> deliveryOpt = deliveryService.getDeliveryById(id);

        if (deliveryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delivery not found");
        }
        Delivery delivery = deliveryOpt.get();
        if (delivery.getDriver() != null) {
            Hibernate.initialize(delivery.getDriver());
        }
        OrderDTO order = null;
        try {
            order = orderServiceClient.getOrderById(delivery.getOrderId());
            System.out.println("Fetched Order: " + order);
        } catch (Exception e) {
            System.err.println("Failed to fetch order details: " + e.getMessage());
        }
        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(delivery, order);

        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping
    @Operation(summary = "Add a new delivery", description = "Creates a new delivery with a driver and returns it with order details")
    public ResponseEntity<DeliveryResponseDTO> addDelivery(@RequestBody Delivery delivery) {
        Delivery savedDelivery = deliveryService.addDelivery(delivery);
        OrderDTO order = fetchOrderDetails(savedDelivery.getOrderId());

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(savedDelivery, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a delivery", description = "Deletes a delivery by its ID")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a delivery", description = "Updates an existing delivery and returns it with order details")
    public ResponseEntity<?> updateDelivery(@PathVariable Long id, @RequestBody Delivery deliveryDetails) {
        Optional<Delivery> existingDeliveryOpt = deliveryService.getDeliveryById(id);

        if (existingDeliveryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delivery not found");
        }

        Delivery updatedDelivery = deliveryService.updateDelivery(id, deliveryDetails);
        OrderDTO order = fetchOrderDetails(updatedDelivery.getOrderId());

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO(updatedDelivery, order);
        return ResponseEntity.ok(responseDTO);
    }
    private OrderDTO fetchOrderDetails(Long orderId) {
        try {
            return orderServiceClient.getOrderById(orderId);
        } catch (Exception e) {
            System.err.println("Failed to fetch order details: " + e.getMessage());
            return null;
        }
    }
}
