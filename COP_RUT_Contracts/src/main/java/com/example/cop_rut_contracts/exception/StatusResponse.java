package com.example.cop_rut_contracts.exception;

public class StatusResponse {
    private String status;
    private String message;

    // Конструктор
    public StatusResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Геттеры и сеттеры
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}