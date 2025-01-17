package com.example.cop_rut_contracts.enam.order;

public enum RedFlag {
    AGGRESSIVE_CLIENT("Проблемы с клиентом"),
    DAMAGED_EQUIPMENT("Повреждено оборудование"),
    LAW_ENFORCEMENT_REQUIRED("Требуется вызов полиции");

    private String value;

    RedFlag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
