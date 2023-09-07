package com.example.zuul.gateway.config;

import com.example.zuul.gateway.config.interceptors.HeaderRequestInterceptor;
import com.example.zuul.gateway.config.interceptors.LoggingOkHttpInterceptor;
import com.example.zuul.gateway.config.interceptors.LoggingRequestInterceptor;
import com.example.zuul.gateway.config.interceptors.LoggingResponseInterceptor;
import okhttp3.OkHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static java.util.List.of;
import static okhttp3.ConnectionSpec.CLEARTEXT;
import static okhttp3.ConnectionSpec.COMPATIBLE_TLS;
import static okhttp3.ConnectionSpec.MODERN_TLS;

@Configuration
@EnableZuulProxy
public class ZuulProxyConfig {

  @Bean
  @Primary
  public OkHttpClient okHttpClient(LoggingOkHttpInterceptor loggingOkHttpInterceptor) {
    return new OkHttpClient.Builder()
      .followRedirects(false)
      .connectionSpecs(of(MODERN_TLS, COMPATIBLE_TLS, CLEARTEXT))
      .addInterceptor(loggingOkHttpInterceptor)
      .build();
  }

  @Bean
  @Primary
  public SimpleHostRoutingFilter simpleHostRoutingFilter(ProxyRequestHelper proxyRequestHelper,
                                                         ZuulProperties zuulProperties,
                                                         CloseableHttpClient customHttpClient) {
    return new SimpleHostRoutingFilter(proxyRequestHelper, zuulProperties, customHttpClient);
  }

  @Bean
  public CloseableHttpClient customHttpClient(HeaderRequestInterceptor headerRequestInterceptor,
                                              LoggingRequestInterceptor loggingRequestInterceptor,
                                              LoggingResponseInterceptor loggingResponseInterceptor) {
    return HttpClients.custom()
      .addInterceptorFirst(headerRequestInterceptor)
      .addInterceptorFirst(loggingRequestInterceptor)
      .addInterceptorLast(loggingResponseInterceptor)
      .build();
  }
}
