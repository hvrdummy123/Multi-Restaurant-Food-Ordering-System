package com.example.orderService.feign;

import com.example.orderService.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService", url = "http://localhost:8080/users")
public interface UserServiceClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
