package com.example.order.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @GetMapping
    public ResponseEntity<String> getMapping() throws InterruptedException {
        // Thread.sleep(60000);
        log.info("OK");
        return ResponseEntity.ok("Hello from order-service");
    }
}
