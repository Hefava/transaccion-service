package com.bootcamp.transaccion_service.infrastructure.configuration;

import com.bootcamp.transaccion_service.domain.utils.TokenHolder;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = TokenHolder.getToken();
            if (token != null && !token.isEmpty()) {
                requestTemplate.header("Authorization", token);
            }
        };
    }
}
