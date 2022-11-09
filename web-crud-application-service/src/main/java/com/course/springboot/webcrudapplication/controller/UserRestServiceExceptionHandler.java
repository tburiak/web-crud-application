package com.course.springboot.webcrudapplication.controller;

import com.course.springboot.webcrudapplication.exception.UserServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class UserRestServiceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        log.error("Wasn't able to handle request. Responding with Internal Server Error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(formErrorMessage(e));
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception e) {
        log.error("Wasn't able to handle request. Responding with Bad Request", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formErrorMessage(e));
    }

    private Map<String, Object> formErrorMessage(Exception e) {
        return Map.of("error", e.getClass().getSimpleName(), "message", e.getMessage());
    }
}
