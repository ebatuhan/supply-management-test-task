package com.batu.supply_management_test_task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batu.supply_management_test_task.exception.FnsErrorDecoder;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;

@Configuration
public class FnsClientConfig {
    
    @Bean
    public RequestInterceptor apiKeyInterceptor(@Value("${fns.api.key}") String apiKey) {
        return requestTemplate -> {
            requestTemplate.query("key", apiKey);
        };
    }
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FnsErrorDecoder();
    }
    
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 1000, 3);
    }
    
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}