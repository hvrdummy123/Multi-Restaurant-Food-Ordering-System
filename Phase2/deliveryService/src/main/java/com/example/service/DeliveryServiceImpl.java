package com.example.service;

import com.example.entity.Delivery;
import com.example.entity.Driver;
import com.example.repository.DeliveryRepository;

import com.example.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    public DeliveryRepository deliveryRepository;
    @Autowired
    public DriverRepository driverRepository;
    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
    @Override
    @Transactional
    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }
    @Override
    public Delivery addDelivery(Delivery delivery) {
        if (delivery.getDriver() != null && delivery.getDriver().getId() != null) {
            Driver driver = driverRepository.findById(delivery.getDriver().getId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            delivery.setDriver(driver);
        } else {
            throw new RuntimeException("Driver ID is required to assign a driver");
        }
        return deliveryRepository.save(delivery);
    }
    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
    @Override
    public Delivery updateDelivery(Long id, Delivery deliveryDetails) {
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setOrderId(deliveryDetails.getOrderId());
            delivery.setStatus(deliveryDetails.getStatus());
            delivery.setEstimatedTime(deliveryDetails.getEstimatedTime());
            if (deliveryDetails.getDriver() != null) {
                Driver driver = driverRepository.findById(deliveryDetails.getDriver().getId())
                        .orElseThrow(() -> new RuntimeException("Driver not found"));
                delivery.setDriver(driver);
            }
            return deliveryRepository.save(delivery);
        }).orElseThrow(() -> new RuntimeException("Delivery not found"));
    }
}