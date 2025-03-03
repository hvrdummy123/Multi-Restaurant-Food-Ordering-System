package com.example.restaurantService.service;

import com.example.restaurantService.entity.Restaurant;
import com.example.restaurantService.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(restaurantDetails.getName());
                    restaurant.setLocation(restaurantDetails.getLocation());
                    restaurant.setContact(restaurantDetails.getContact());
                    restaurant.setRating(restaurantDetails.getRating());
                    return restaurantRepository.save(restaurant);
                }).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}