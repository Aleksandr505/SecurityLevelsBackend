package com.example.securitylevels.model.domain;

public record LevelKey(
        ThreatLevel threatLevel,
        SubjectVolume volume,
        boolean isEmployee
) { }
