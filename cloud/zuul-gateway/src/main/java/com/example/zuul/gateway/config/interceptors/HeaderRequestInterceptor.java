package com.example.zuul.gateway.config.interceptors;

import com.example.zuul.gateway.utils.MdcService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeaderRequestInterceptor implements HttpRequestInterceptor {

    private final MdcService mdcService;
    private final ProxyHeadersConfig proxyHeadersConfig;

    @Override
    public void process(HttpRequest request, HttpContext context) {
        proxyHeadersConfig.getHeaders()
            .forEach(header -> mdcService.get(header).ifPresent(value -> request.addHeader(header, value)));
    }
}
