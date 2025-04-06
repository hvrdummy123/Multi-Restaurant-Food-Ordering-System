package com.example.service;

import com.example.entity.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();
    Optional<Delivery> getDeliveryById(Long id);
    Delivery addDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    Delivery updateDelivery(Long id, Delivery deliveryDetails);
}