package com.example.cop_rut.model.enam.order;

public enum CleaningType {
        STANDARD("Стандартная"),
        GENERAL("Генеральная"),
        POST_RENOVATION("После ремонта");

    private String value;

    CleaningType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
