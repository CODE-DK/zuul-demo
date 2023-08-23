package com.example.zuul.gateway.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;
import java.util.Set;

import static java.util.Set.of;

@Slf4j
public class LoggingInterceptor implements Interceptor {

    private static final Set<String> METHODS = of("POST", "PUT", "PATCH");

    @Override
    public @NonNull Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        log.info("Send request {}: {}\nheaders: {}", method, request.url(), request.headers());
        if (METHODS.contains(method)) {
            request = request.newBuilder()
                    .method(method, request.body())
                    .build();
            Buffer buffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(buffer);
            }
            log.info("Request body:\n{}", buffer.readUtf8());
        }
        Response response = chain.proceed(request);
        log.info("Received response {}: {}\nheaders: {}", method, response.request().url(), response.headers());
        return response;
    }
}

