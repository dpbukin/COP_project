package com.example.cop_rut_contracts.exception;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super("Internal server error: " + message);
    }
}
