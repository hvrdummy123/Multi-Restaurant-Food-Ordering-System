<<<<<<< HEAD
package com.example.restaurantService.service;

import com.example.restaurantService.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId);
    MenuItem addMenuItem(MenuItem menuItem);
    void deleteMenuItem(Long id);
=======
package com.example.restaurantService.service;

import com.example.restaurantService.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId);
    MenuItem addMenuItem(MenuItem menuItem);
    void deleteMenuItem(Long id);
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
}