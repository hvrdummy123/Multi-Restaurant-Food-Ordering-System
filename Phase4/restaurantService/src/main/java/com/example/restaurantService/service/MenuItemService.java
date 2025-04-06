package com.example.restaurantService.service;

import com.example.restaurantService.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId);
    MenuItem addMenuItem(MenuItem menuItem);
    void deleteMenuItem(Long id);
}