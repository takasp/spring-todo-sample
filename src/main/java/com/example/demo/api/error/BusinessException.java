package com.example.demo.api.error;

import org.springframework.core.NestedRuntimeException;

public class BusinessException extends NestedRuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
