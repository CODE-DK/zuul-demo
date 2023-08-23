package com.example.zuul.gateway.config;

import feign.Logger.Level;
import feign.Request.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static feign.Logger.Level.FULL;
import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignClientConfig {

    @Bean
    public Level feignLoggerLevel() {
        return FULL;
    }

    @Bean
    public Options options() {
        return new Options(5, SECONDS, 5, SECONDS, false);
    }
}
