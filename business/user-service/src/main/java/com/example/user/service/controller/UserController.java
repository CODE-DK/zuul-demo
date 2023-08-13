package com.example.user.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@Slf4j
@RestController
public class UserController {

    @GetMapping
    public Callable<ResponseEntity<String>> getMapping() throws InterruptedException {
        return () -> {
            Thread.sleep(60000);
            log.info("OK");
            return ResponseEntity.ok("Hello from user-service");
        };
    }
}
