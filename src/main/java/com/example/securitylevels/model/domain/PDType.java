package com.example.securitylevels.model.domain;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Stream;

public enum PDType {
    INYE("Иные", Map.of(
            ThreatLevel.ONE, Map.of(SubjectVolume.GT, SecurityLevel.A1, SubjectVolume.LT, SecurityLevel.A1),
            ThreatLevel.TWO, Map.of(SubjectVolume.GT, SecurityLevel.D2, SubjectVolume.LT, SecurityLevel.B3),
            ThreatLevel.THREE, Map.of(SubjectVolume.GT, SecurityLevel.D3, SubjectVolume.LT, SecurityLevel.B4)
    )),
    SPECIAL("Специальные категории", Map.of(
            ThreatLevel.ONE, Map.of(SubjectVolume.GT, SecurityLevel.A1, SubjectVolume.LT, SecurityLevel.A1),
            ThreatLevel.TWO, Map.of(SubjectVolume.GT, SecurityLevel.B1, SubjectVolume.LT, SecurityLevel.B2),
            ThreatLevel.THREE, Map.of(SubjectVolume.GT, SecurityLevel.E2, SubjectVolume.LT, SecurityLevel.V3)
    )),
    BIOMETRIC("Биометрические", Map.of(
            ThreatLevel.ONE, Map.of(SubjectVolume.GT, SecurityLevel.A1, SubjectVolume.LT, SecurityLevel.A1),
            ThreatLevel.TWO, Map.of(SubjectVolume.GT, SecurityLevel.V2, SubjectVolume.LT, SecurityLevel.V2),
            ThreatLevel.THREE, Map.of(SubjectVolume.GT, SecurityLevel.G3, SubjectVolume.LT, SecurityLevel.G3)
    )),
    PUBLIC("Общедоступные", Map.of(
            ThreatLevel.ONE, Map.of(SubjectVolume.GT, SecurityLevel.A2, SubjectVolume.LT, SecurityLevel.A2),
            ThreatLevel.TWO, Map.of(SubjectVolume.GT, SecurityLevel.G2, SubjectVolume.LT, SecurityLevel.A3),
            ThreatLevel.THREE, Map.of(SubjectVolume.GT, SecurityLevel.A4, SubjectVolume.LT, SecurityLevel.B4)
    ));

    @Getter
    private final String displayName;
    private final Map<ThreatLevel, Map<SubjectVolume, SecurityLevel>> levelMap;

    PDType(String displayName, Map<ThreatLevel, Map<SubjectVolume, SecurityLevel>> levelMap) {
        this.displayName = displayName;
        this.levelMap = levelMap;
    }

    public SecurityLevel getLevel(ThreatLevel threat, SubjectVolume volume) {
        return levelMap.get(threat).get(volume);
    }

    public static PDType fromDisplayName(String name) {
        return Stream.of(values())
                .filter(e -> e.displayName.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown PD type: " + name));
    }
}
