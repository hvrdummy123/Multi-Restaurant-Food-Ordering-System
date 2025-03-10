package com.example.restaurantService.controller;

import com.example.restaurantService.UserDTO;
import com.example.restaurantService.UserFeignClient;
import com.example.restaurantService.entity.MenuItem;
import com.example.restaurantService.service.MenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/menu")
@Tag(name = "Menu API", description = "Endpoints for managing menu items in a restaurant")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Operation(summary = "Get all menu items for a restaurant", description = "Fetch a list of all menu items belonging to a specific restaurant")
    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurantId(restaurantId);
        return ResponseEntity.ok(menuItems);
    }

    @Operation(summary = "Add a new menu item", description = "Create a new menu item under a specific restaurant")
    @PostMapping
    public ResponseEntity<?> addMenuItem(
            @RequestHeader("userId") Long userId,
            @RequestParam Long restaurantId,
            @RequestBody MenuItem menuItem) {

        UserDTO user = userFeignClient.getUserById(userId);

        if (!isAuthorized(user, "ADMIN", "OWNER")) {
            return ResponseEntity.status(403).body("Unauthorized: Only restaurant owners or admins can add menu items.");
        }

        menuItem.setRestaurantId(restaurantId);
        MenuItem createdMenuItem = menuItemService.addMenuItem(menuItem);
        return ResponseEntity.ok(createdMenuItem);
    }

    @Operation(summary = "Delete a menu item", description = "Remove a menu item from the menu")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@RequestHeader("userId") Long userId, @PathVariable Long id) {
        UserDTO user = userFeignClient.getUserById(userId);

        if (!isAuthorized(user, "ADMIN", "OWNER")) {
            return ResponseEntity.status(403).body("Unauthorized: Only restaurant owners or admins can delete menu items.");
        }

        menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok("Menu item deleted successfully.");
    }

    private boolean isAuthorized(UserDTO user, String... allowedRoles) {
        return user != null && List.of(allowedRoles).contains(user.getRole());
    }
}
