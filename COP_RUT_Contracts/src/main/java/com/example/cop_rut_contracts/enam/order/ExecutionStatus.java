package com.example.cop_rut_contracts.enam.order;

public enum ExecutionStatus {
    COMPLETED("Завершено"),
    PROCESSING("В процессе"),
    ACCEPTED("В обработке");
    private String value;

    ExecutionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
