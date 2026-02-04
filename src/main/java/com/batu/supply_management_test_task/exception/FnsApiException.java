package com.batu.supply_management_test_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FnsApiException extends ResponseStatusException {
    public FnsApiException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }
}