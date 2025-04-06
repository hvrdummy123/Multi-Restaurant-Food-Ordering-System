<<<<<<< HEAD
package com.example.restaurantService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "userService", url = "http://localhost:8080/users") // Replace with actual User Service URL
@FeignClient(name = "USERSERVICE") // Replace with actual User Service URL

public interface UserFeignClient {
    @GetMapping("/users/{userId}")
    UserDTO getUserById(@PathVariable Long userId);
}
=======
package com.example.restaurantService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "userService", url = "http://localhost:8080/users") // Replace with actual User Service URL
@FeignClient(name = "USERSERVICE") // Replace with actual User Service URL

public interface UserFeignClient {
    @GetMapping("/users/{userId}")
    UserDTO getUserById(@PathVariable Long userId);
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
