package com.example.zuul.gateway.filters.pre;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class LogRequestPreFilter extends PreFilter {

    @Override
    protected void onActive() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Request Method: {}", request.getMethod());
        log.info("Request URL: {}", request.getRequestURL());
    }
}
