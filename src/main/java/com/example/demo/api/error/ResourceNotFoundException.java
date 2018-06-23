package com.example.demo.api.error;

import org.springframework.core.NestedRuntimeException;

public class ResourceNotFoundException extends NestedRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
