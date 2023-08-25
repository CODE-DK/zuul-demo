package com.example.zuul.gateway.filters.pre;

import com.example.zuul.gateway.config.interceptors.ProxyHeadersConfig;
import com.example.zuul.gateway.utils.MdcService;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogRequestPreFilter extends PreFilter {

    private final MdcService mdcService;
    private final ProxyHeadersConfig proxyHeadersConfig;

    @Override
    protected void onActive() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        proxyHeadersConfig.getHeaders()
            .forEach(header -> ofNullable(request.getHeader(header)).ifPresent(value -> mdcService.put(header, value)));
        log.info("Request Method: {}", request.getMethod());
        log.info("Request URL: {}", request.getRequestURL());
    }
}
