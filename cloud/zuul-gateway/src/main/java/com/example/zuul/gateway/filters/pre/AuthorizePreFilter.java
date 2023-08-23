package com.example.zuul.gateway.filters.pre;

import com.example.zuul.gateway.clients.AuthServiceClient;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthorizePreFilter extends PreFilter {

    private final AuthServiceClient authServiceClient;

    @Override
    protected void onActive() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        String bearerToken = requestContext.getRequest().getHeader("Authorization");
        if (bearerToken == null) {
            ResponseEntity<UUID> token = authServiceClient.getAuthorizationHeader();
            requestContext.getResponse().addHeader("Authorization", "Bearer " + token.getBody());
        }
    }
}
