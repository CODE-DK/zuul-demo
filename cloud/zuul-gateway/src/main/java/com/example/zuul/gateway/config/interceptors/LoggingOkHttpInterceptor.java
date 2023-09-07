package com.example.zuul.gateway.config.interceptors;

import com.example.zuul.gateway.utils.MdcService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

import static java.util.Set.of;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingOkHttpInterceptor implements Interceptor {

  private static final Set<String> METHODS = of("POST", "PUT", "PATCH");

  private final MdcService mdcService;
  private final ProxyHeadersConfig proxyHeadersConfig;

  @Override
  public @NonNull Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    String method = request.method();
    Request.Builder builder = request.newBuilder();
    proxyHeadersConfig.getHeaders()
      .forEach(header -> mdcService.get(header).ifPresent(value -> builder.addHeader(header, value)));
    request = builder.build();
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

