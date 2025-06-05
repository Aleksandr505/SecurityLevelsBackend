package com.example.securitylevels.model.dto;

import com.example.securitylevels.model.domain.gis.GisMeasures;

import java.util.List;
import java.util.Map;

public class LevelResponse {
    private String maxLevel;
    private String reason;
    private Map<String, List<GisMeasures.Measure>> measures;

    public LevelResponse() {
    }

    public LevelResponse(String maxLevel, String reason, Map<String, List<GisMeasures.Measure>> measures) {
        this.maxLevel = maxLevel;
        this.reason = reason;
        this.measures = measures;
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

    public Map<String, List<GisMeasures.Measure>> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, List<GisMeasures.Measure>> measures) {
        this.measures = measures;
    }
}
