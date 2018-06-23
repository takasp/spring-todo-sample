package com.example.demo.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "System error is occurred",
                request.getDescription(false));
        return super.handleExceptionInternal(ex, exceptionResponse, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return super.handleExceptionInternal(ex, exceptionResponse, null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return super.handleExceptionInternal(ex, exceptionResponse, null, HttpStatus.CONFLICT, request);
    }
}
