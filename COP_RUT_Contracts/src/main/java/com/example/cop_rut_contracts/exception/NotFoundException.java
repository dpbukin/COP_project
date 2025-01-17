package com.example.cop_rut_contracts.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Not found: " + message);
    }
}
