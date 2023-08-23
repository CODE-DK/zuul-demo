package com.example.zuul.gateway.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ZuulProxyConfig {

    @Bean
    @Primary
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .followRedirects(false)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }
}
