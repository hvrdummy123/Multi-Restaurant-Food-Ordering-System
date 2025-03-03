package com.example.service;

import com.example.entity.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverById(Long id);
    Driver addDriver(Driver driver);
    void deleteDriver(Long id);
    Driver updateDriver(Long id, Driver driverDetails);
}
