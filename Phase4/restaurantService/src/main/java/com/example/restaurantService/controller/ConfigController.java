<<<<<<< HEAD
package com.example.restaurantService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @GetMapping("/db-url")
    public String getDbUrl() {
        return "Datasource: " + datasourceUrl;
    }
}
=======
package com.example.restaurantService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @GetMapping("/db-url")
    public String getDbUrl() {
        return "Datasource: " + datasourceUrl;
    }
}
>>>>>>> 2fbcaacacf3d6a76ff652418bf7f214fa5faeb94
