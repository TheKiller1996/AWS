package com.infor.AWS.exception;

import com.infor.AWS.config.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException resourceNotFoundException){
        log.warn("Resource not found {}", resourceNotFoundException.getMessage());
        return ResponseBuilder.notFound("Resource is unavailable");
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicateResource(DuplicateResourceException duplicateResourceException){
        log.warn("Duplicate Resources found {}", duplicateResourceException.getMessage());
        return ResponseBuilder.notFound("Duplicate Resources found");
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException invalidCredentialsException){
        log.warn("Invalid credential{}", invalidCredentialsException.getMessage());
        return ResponseBuilder.unauthorized("Invalid credential");
    }
}
