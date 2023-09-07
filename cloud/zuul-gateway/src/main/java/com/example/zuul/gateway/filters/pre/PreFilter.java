package com.example.zuul.gateway.filters.pre;

import com.example.zuul.gateway.filters.MyZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

@Slf4j
abstract class PreFilter extends MyZuulFilter {

  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }
}
