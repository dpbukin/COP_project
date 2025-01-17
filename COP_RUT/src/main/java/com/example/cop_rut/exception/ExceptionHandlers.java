package com.example.cop_rut.exception;

import com.example.cop_rut_contracts.exception.InternalServerErrorException;
import com.example.cop_rut_contracts.exception.InvalidArgumentException;
import com.example.cop_rut_contracts.exception.NotFoundException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class ExceptionHandlers {

    private static final String ERROR_STATUS = "error";

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<StatusResponse> handleInvalidArgumentException(InvalidArgumentException e) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StatusResponse> handleNotFoundException(NotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, e.getMessage()));
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<StatusResponse> handleInternalServerErrorException(InternalServerErrorException e) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, e.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StatusResponse> handleGenericException(Exception e) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new StatusResponse(ERROR_STATUS, "An unexpected error occurred: " + e.getMessage()));
    }
}
