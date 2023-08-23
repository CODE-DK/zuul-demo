package com.example.order.service.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    @Value("${spring.application.name}")
    private String applicationName;
    private final EurekaClient eurekaClient;

    @GetMapping
    public ResponseEntity<String> getMapping() {
        return ResponseEntity.ok("Hello from order-service");
    }

    @GetMapping("/base-url")
    public ResponseEntity<String> getBaseUrl() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(applicationName, false);
        return ResponseEntity.ok(instanceInfo.getHomePageUrl());
    }
}
