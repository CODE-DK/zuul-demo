package com.example.zuul.gateway.clients;

import com.example.zuul.gateway.clients.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(value = "auth-service", configuration = FeignClientConfig.class)
public interface AuthServiceClient {

    @PostMapping("/getAuthorizationHeader")
    ResponseEntity<UUID> getAuthorizationHeader();
}
