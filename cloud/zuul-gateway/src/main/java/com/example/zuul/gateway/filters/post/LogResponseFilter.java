package com.example.zuul.gateway.filters.post;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogResponseFilter extends PostFilter {

    @Override
    protected void onActive() {
        RequestContext ctx = RequestContext.getCurrentContext();
        int status = ctx.getResponseStatusCode();
        log.info("Response Status Code: {}", status);
        ctx.getResponse().addHeader("trace-id", ctx.getRequest().getHeader("trace-id"));
    }
}
