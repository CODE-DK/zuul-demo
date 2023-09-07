package com.example.zuul.gateway.utils;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MdcService {

  public void put(String key, String value) {
    try {
      MDC.put(key, value);
    } catch (Exception e) {
      // ignore
    }
  }

  public void remove(String key) {
    try {
      MDC.remove(key);
    } catch (Exception e) {
      // ignore
    }
  }

  public Optional<String> get(String key) {
    try {
      return Optional.ofNullable(MDC.get(key));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public void clear() {
    try {
      MDC.clear();
    } catch (Exception e) {
      // ignore
    }
  }
}
