package com.example.zuul.gateway.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggingRequestInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest request, HttpContext context) {
        String message = buildRequestEntry(request, context)
            + buildHeadersEntry(request.getAllHeaders());
        log.info(message);
    }


    private String buildRequestEntry(HttpRequest request, HttpContext context) {
        return "\nRequest - "
            + request.getRequestLine().getMethod() + " "
            + context.getAttribute("http.target_host")
            + request.getRequestLine().getUri();
    }

    private String buildHeadersEntry(Header[] headers) {
        return "\nHeaders: ["
            + Arrays.stream(headers)
            .map(header -> header.getName() + ": " + header.getValue())
            .collect(Collectors.joining(", "))
            + "]";
    }
}
