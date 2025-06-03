package com.example.securitylevels.model.domain.gis;

public enum ScaleLevel {
    FEDERAL("Федеральный"),
    REGIONAL("Региональный"),
    OBJECT("Объектный");

    private final String name;

    ScaleLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
