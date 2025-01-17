package com.example.cop_rut_contracts.enam.room;

public enum SpaceType {
    FLAT("Квартира"),
    OFFICE("Офис"),
    DINING("Столовая"),
    WAREHOUSE("Склад"),
    FACTORY_FLOOR("Производственный цех"),
    LABORATORY("Лаборатория"),
    GARAGE("Гараж"),
    WORKSHOP("Мастерска");

    private String value;
    SpaceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
