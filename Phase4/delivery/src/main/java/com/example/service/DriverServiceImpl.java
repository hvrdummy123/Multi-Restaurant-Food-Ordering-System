package com.example.service;

import com.example.entity.Driver;
import com.example.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    public DriverRepository driverRepository;
    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    @Override
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }
    @Override
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
    @Override
    public Driver updateDriver(Long id, Driver driverDetails) {
        return driverRepository.findById(id).map(driver -> {
            driver.setName(driverDetails.getName());
            driver.setPhoneNumber(driverDetails.getPhoneNumber());
            driver.setVehicleDetails(driverDetails.getVehicleDetails());
            driver.setStatus(driverDetails.getStatus());
            return driverRepository.save(driver);
        }).orElseThrow(() -> new RuntimeException("Driver not found"));
    }
}
