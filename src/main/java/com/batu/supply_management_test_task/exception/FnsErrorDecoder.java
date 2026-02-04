package com.batu.supply_management_test_task.exception;

import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FnsErrorDecoder implements ErrorDecoder {
    
    private final ErrorDecoder defaultDecoder = new Default();


    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401 || response.status() == 403) {
            return new FnsApiException("Invalid API key or unauthorized", HttpStatus.UNAUTHORIZED);
        }
        if (response.status() == 404) {
            return new FnsApiException("Company not found", HttpStatus.NOT_FOUND);
        }
        if (response.status() >= 500) {
            return new FnsApiException("FNS API service unavailable", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return defaultDecoder.decode(methodKey, response);
    }
}
