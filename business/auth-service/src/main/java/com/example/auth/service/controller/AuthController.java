package com.example.auth.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class AuthController {

    @PostMapping("/getAuthorizationHeader")
    public ResponseEntity<UUID> getAuthorizationHeader() throws InterruptedException {
        // Thread.sleep(60000);
        UUID token = UUID.randomUUID();
        log.info("Generate new token: {}", token);
        return ResponseEntity.ok(token);
    }
}
