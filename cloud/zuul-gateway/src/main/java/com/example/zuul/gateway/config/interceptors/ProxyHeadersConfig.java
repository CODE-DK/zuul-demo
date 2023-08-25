package com.example.zuul.gateway.config.interceptors;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@Value
@ConstructorBinding
@ConfigurationProperties("request.proxy")
public class ProxyHeadersConfig {
    List<String> headers;
}
