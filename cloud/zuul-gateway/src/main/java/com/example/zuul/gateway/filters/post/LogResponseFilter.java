package com.example.zuul.gateway.filters.post;

import com.example.zuul.gateway.config.interceptors.ProxyHeadersConfig;
import com.example.zuul.gateway.utils.MdcService;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogResponseFilter extends PostFilter {

    private final MdcService mdcService;
    private final ProxyHeadersConfig proxyHeadersConfig;

    @Override
    protected void onActive() {
        RequestContext ctx = RequestContext.getCurrentContext();
        int status = ctx.getResponseStatusCode();
        log.info("Response Status Code: {}", status);
        proxyHeadersConfig.getHeaders()
            .forEach(header -> mdcService.get(header).ifPresent(value -> ctx.getResponse().addHeader(header, value)));
        mdcService.clear();
    }
}
