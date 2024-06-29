package com.hmall.api.config;

import com.hmall.common.utils.UserContext;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import feign.Logger;

public class DefaultFeignConfig {
  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public RequestInterceptor userInfoRequestIntercepter() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate template) {
        Long userId = UserContext.getUser();
        if (userId != null) {
          template.header("user-info", userId.toString());
        }
      }
    };
  }
}
