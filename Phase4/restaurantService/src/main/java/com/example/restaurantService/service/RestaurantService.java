<<<<<<< HEAD
package com.example.restaurantService.service;

import com.example.restaurantService.entity.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Optional<Restaurant> getRestaurantById(Long id);
    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long id, Restaurant restaurantDetails);
    void deleteRestaurant(Long id);
}
=======
package com.example.restaurantService.service;

import com.example.restaurantService.entity.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Optional<Restaurant> getRestaurantById(Long id);
    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long id, Restaurant restaurantDetails);
    void deleteRestaurant(Long id);
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
