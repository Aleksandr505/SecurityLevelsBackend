package com.example.securitylevels.model.dto;

import lombok.*;

public class LevelResponse {
    private String maxLevel;
    private String reason;

    public LevelResponse() {
    }

    public LevelResponse(String maxLevel, String reason) {
        this.maxLevel = maxLevel;
        this.reason = reason;
    }

    public String getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(String maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
