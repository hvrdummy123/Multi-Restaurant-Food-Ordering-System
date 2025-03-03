package com.example.orderService.feign;

import com.example.orderService.dto.RestaurantDTO;
import com.example.orderService.dto.MenuItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081")
public interface RestaurantServiceClient {

    @GetMapping("/restaurants/{id}")
    RestaurantDTO getRestaurantById(@PathVariable Long id);

    @GetMapping("/restaurants/{id}/menu")
    List<MenuItemDTO> getMenuByRestaurantId(@PathVariable Long id);
}
