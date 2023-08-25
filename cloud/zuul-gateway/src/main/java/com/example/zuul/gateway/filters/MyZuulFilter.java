package com.example.zuul.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MyZuulFilter extends ZuulFilter {

    protected int order = 0;
    protected boolean isActive = true;

    @Override
    public boolean shouldFilter() {
        return isActive();
    }

    @Override
    public Object run() {
        try {
            onActive();
            return null;
        } catch (Exception e) {
            log.error("Error occurred in filter", e);
            return null;
        }
    }

    @Override
    public int filterOrder() {
        return order;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected abstract void onActive() throws Exception;
}
