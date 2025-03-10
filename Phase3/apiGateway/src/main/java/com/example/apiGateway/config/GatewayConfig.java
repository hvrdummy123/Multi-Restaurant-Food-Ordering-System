package com.example.apiGateway.config;

import com.example.apiGateway.filters.JwtAuthFilter;
import com.example.apiGateway.filters.RateLimiterFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class GatewayConfig {
}

