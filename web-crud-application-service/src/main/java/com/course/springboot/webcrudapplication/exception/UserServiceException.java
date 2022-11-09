package com.course.springboot.webcrudapplication.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
