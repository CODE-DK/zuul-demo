package com.example.zuul.gateway.filters;

import com.example.zuul.gateway.clients.AuthServiceClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PreAuthorizeFilter extends ZuulFilter {

    private final AuthServiceClient authServiceClient;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();

        String bearerToken = requestContext.getRequest().getHeader("Authorization");
        if (bearerToken == null) {
            ResponseEntity<UUID> token = authServiceClient.getAuthorizationHeader();
            requestContext.getResponse().addHeader("Authorization", "Bearer " + token.getBody());
        }
        return null;
    }
}
