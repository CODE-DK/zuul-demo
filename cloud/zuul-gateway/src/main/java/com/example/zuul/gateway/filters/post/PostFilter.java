package com.example.zuul.gateway.filters.post;

import com.example.zuul.gateway.filters.MyZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

@Slf4j
abstract class PostFilter extends MyZuulFilter {

  @Override
  public String filterType() {
    return FilterConstants.POST_TYPE;
  }
}
