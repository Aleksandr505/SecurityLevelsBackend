package com.example.securitylevels.model.domain;

public enum ThreatLevel {
    ONE(1),
    TWO(2),
    THREE(3);

    private final int code;

    ThreatLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
