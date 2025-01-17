package com.example.cop_rut.model.enam.order;

public enum AdditionalService{
    WINDOW_CLEANING("Мытье окон"),
    CARPET_CLEANING("Очистка ковров"),
    FURNITURE_CLEANING("Очистка мебели");
    private String value;

    AdditionalService(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
