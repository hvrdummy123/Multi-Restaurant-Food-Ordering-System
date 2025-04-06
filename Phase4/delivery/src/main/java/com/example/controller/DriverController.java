package com.example.controller;

import com.example.service.DriverService;
import com.example.entity.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
@Tag(name = "Drivers", description = "APIs for managing drivers")
public class DriverController {
    @Autowired
    public DriverService driverService;
    @GetMapping
    @Operation(summary = "Get all drivers", description = "Fetches the list of all drivers")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get driver by ID", description = "Fetches a driver by its unique ID")
    public Optional<Driver> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }
    @PostMapping
    @Operation(summary = "Add a new driver", description = "Creates a new driver record")
    public Driver addDriver(@RequestBody Driver driver) {
        return driverService.addDriver(driver);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a driver", description = "Deletes a driver by their ID")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a driver", description = "Updates an existing driver’s details")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driverDetails) {
        return driverService.updateDriver(id, driverDetails);
    }
}

