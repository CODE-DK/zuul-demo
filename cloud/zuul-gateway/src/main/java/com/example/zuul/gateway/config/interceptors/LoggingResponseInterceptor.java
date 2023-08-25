package com.example.zuul.gateway.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggingResponseInterceptor implements HttpResponseInterceptor {

    @Override
    public void process(HttpResponse response, HttpContext context) {
        String message = buildStatusEntry(response)
            + buildHeadersEntry(response.getAllHeaders());
        log.info(message);
    }

    private String buildStatusEntry(HttpResponse response) {
        return "\nResponse - "
            + response.getStatusLine().getStatusCode() + " "
            + response.getStatusLine().getReasonPhrase();
    }

    private String buildHeadersEntry(Header[] headers) {
        return "\nHeaders: ["
            + Arrays.stream(headers)
            .map(header -> header.getName() + ": " + header.getValue())
            .collect(Collectors.joining(", "))
            + "]";
    }
}
