package com.example.cop_rut.model.enam.order;

public enum DistanceFromCenter {
    NEAR("До 20 км"),
    MEDIUM("До 50 км"),
    FAR("Более 50 км");
    private String value;

    DistanceFromCenter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
