package com.example.zuul.gateway.filters.post;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class AuthorizePostFilter extends PostFilter {

  @Override
  protected void onActive() {
    RequestContext requestContext = RequestContext.getCurrentContext();

    //todo: not implemented
  }
}
