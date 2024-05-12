package com.att.tdp.bisbis10.service.exception;

// Custom exception class to represent a resource not found error
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
