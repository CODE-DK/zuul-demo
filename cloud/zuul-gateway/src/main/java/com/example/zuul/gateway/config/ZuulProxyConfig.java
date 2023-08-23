package com.example.zuul.gateway.config;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static java.util.List.of;

@Configuration
public class ZuulProxyConfig {

    @Bean
    @Primary
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .followRedirects(false)
                .connectionSpecs(of(spec()))
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    private ConnectionSpec spec() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .build();
    }
}
