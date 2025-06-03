package com.example.securitylevels.model.dto;

import com.example.securitylevels.model.domain.gis.GisMeasures;

import java.util.List;
import java.util.Map;

public class GisResponse {
    private Integer protectClass;
    private Map<String, List<GisMeasures.Measure>> measures;

    public GisResponse() {
    }

    public GisResponse(Integer protectClass, Map<String, List<GisMeasures.Measure>> measures) {
        this.protectClass = protectClass;
        this.measures = measures;
    }

    public Integer getProtectClass() {
        return protectClass;
    }

    public void setProtectClass(Integer protectClass) {
        this.protectClass = protectClass;
    }

    public Map<String, List<GisMeasures.Measure>> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, List<GisMeasures.Measure>> measures) {
        this.measures = measures;
    }
}
