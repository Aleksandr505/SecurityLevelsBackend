package com.example.securitylevels.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class LevelRequest {
    @NotNull
    private String certOs;
    @NotNull
    private String certApp;
    @NotNull
    private String network;
    @NotNull
    private String number;
    @NotEmpty
    private List<String> selectedOptions;
    @NotNull
    @JsonProperty("isEmployee")
    private Boolean isEmployee;

    public LevelRequest() {
    }

    public LevelRequest(String certOs, String certApp, String network, String number, List<String> selectedOptions, Boolean isEmployee) {
        this.certOs = certOs;
        this.certApp = certApp;
        this.network = network;
        this.number = number;
        this.selectedOptions = selectedOptions;
        this.isEmployee = isEmployee;
    }

    public @NotNull String getCertOs() {
        return certOs;
    }

    public void setCertOs(@NotNull String certOs) {
        this.certOs = certOs;
    }

    public @NotNull String getCertApp() {
        return certApp;
    }

    public void setCertApp(@NotNull String certApp) {
        this.certApp = certApp;
    }

    public @NotNull String getNetwork() {
        return network;
    }

    public void setNetwork(@NotNull String network) {
        this.network = network;
    }

    public @NotNull String getNumber() {
        return number;
    }

    public void setNumber(@NotNull String number) {
        this.number = number;
    }

    public @NotEmpty List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(@NotEmpty List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public @NotNull Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(@NotNull Boolean employee) {
        isEmployee = employee;
    }
}
