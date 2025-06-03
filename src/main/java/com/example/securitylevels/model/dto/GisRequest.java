package com.example.securitylevels.model.dto;

import com.example.securitylevels.model.domain.gis.ScaleLevel;
import jakarta.validation.constraints.NotNull;

public class GisRequest {
    @NotNull
    private Integer level;
    @NotNull
    private ScaleLevel scale;

    public GisRequest() {
    }

    public GisRequest(Integer level, ScaleLevel scale) {
        this.level = level;
        this.scale = scale;
    }

    public @NotNull Integer getLevel() {
        return level;
    }

    public void setLevel(@NotNull Integer level) {
        this.level = level;
    }

    public @NotNull ScaleLevel getScale() {
        return scale;
    }

    public void setScale(@NotNull ScaleLevel scale) {
        this.scale = scale;
    }
}
