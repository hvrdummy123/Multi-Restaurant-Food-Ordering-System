package com.example.restaurantService.controller;

import com.example.restaurantService.UserDTO;
import com.example.restaurantService.UserFeignClient;
import com.example.restaurantService.entity.Restaurant;
import com.example.restaurantService.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurant API", description = "Endpoints for managing restaurants")  // Swagger Tag for the Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserFeignClient userFeignClient; // Injecting Feign Client

    @Operation(summary = "Get all restaurants", description = "Fetch a list of all available restaurants")
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @Operation(summary = "Get restaurant by ID", description = "Fetch details of a specific restaurant by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Restaurant>> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.isPresent() ? ResponseEntity.ok(restaurant) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new restaurant", description = "Create a new restaurant entry")
    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant, @RequestHeader("userId") Long userId) {
        UserDTO user = userFeignClient.getUserById(userId);  // Fetch user details from User Service
        if (!isAuthorized(user, "ADMIN", "OWNER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied! You are not authorized.");
        }
        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    @Operation(summary = "Update a restaurant", description = "Modify details of an existing restaurant")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurantDetails, @RequestHeader("userId") Long userId) {
        UserDTO user = userFeignClient.getUserById(userId);
        if (!isAuthorized(user, "ADMIN", "OWNER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied! You are not authorized.");
        }
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurantDetails);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @Operation(summary = "Delete a restaurant", description = "Remove a restaurant from the system")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id, @RequestHeader("userId") Long userId) {
        UserDTO user = userFeignClient.getUserById(userId);
        if (!isAuthorized(user, "ADMIN")) {  // Only Admin can delete
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied! You are not authorized.");
        }
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted successfully.");
    }

    private boolean isAuthorized(UserDTO user, String... allowedRoles) {
        return user != null && List.of(allowedRoles).contains(user.getRole());
    }


}
