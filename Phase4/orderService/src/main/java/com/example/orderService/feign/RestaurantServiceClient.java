<<<<<<< HEAD
package com.example.orderService.feign;

import com.example.orderService.dto.MenuItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "restaurantService", url = "http://localhost:8081")
public interface RestaurantServiceClient {

    @GetMapping("/menu/{restaurantId}")
    List<MenuItemDTO> getMenuItems(@PathVariable Long restaurantId);
}
=======
package com.example.orderService.feign;

import com.example.orderService.dto.MenuItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "restaurantService", url = "http://localhost:8081")
public interface RestaurantServiceClient {

    @GetMapping("/menu/{restaurantId}")
    List<MenuItemDTO> getMenuItems(@PathVariable Long restaurantId);
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
