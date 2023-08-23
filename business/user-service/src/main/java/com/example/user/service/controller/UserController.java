package com.example.user.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @GetMapping
    public ResponseEntity<String> getMapping() {
        return ResponseEntity.ok("Hello from user-service");
    }
}
