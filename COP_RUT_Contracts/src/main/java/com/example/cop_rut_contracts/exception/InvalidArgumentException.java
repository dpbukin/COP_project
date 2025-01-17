package com.example.cop_rut_contracts.exception;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super("Invalid request: " + message);
    }
}