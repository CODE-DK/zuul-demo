package com.example.user.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

  public static final String ERROR_MESSAGE = "Oops, something went wrong in user service";

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<String> handleGenericException(Exception e) {
    log.error(ERROR_MESSAGE, e);
    return new ResponseEntity<>(ERROR_MESSAGE, INTERNAL_SERVER_ERROR);
  }
}
