package com.example.securitylevels.model.domain;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.entry;

/**
 * Типы персональных данных (ПД), используемые для определения уровня защищённости информационных систем.
 * Каждый тип ПД содержит LevelKey, определяющий необходимый уровень защиты в зависимости от:
 * - типа угрозы (1, 2 или 3),
 * - объёма обрабатываемых субъектов (более или менее 100 тыс.),
 * - наличия среди субъектов сотрудников оператора.
 * Пример: тип INYE (Иные ПД) может требовать разные уровни защищённости в зависимости от условий.
 */
public enum PDType {
    INYE("Иные", Map.ofEntries(
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, true), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, false), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, true), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, false), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, true), SecurityLevel.B3),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, false), SecurityLevel.D2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, true), SecurityLevel.B3),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, false), SecurityLevel.B3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, true), SecurityLevel.B4),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, false), SecurityLevel.D3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, true), SecurityLevel.B4),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, false), SecurityLevel.B4)
    )),
    SPECIAL("Специальные категории", Map.ofEntries(
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, true), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, false), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, true), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, false), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, true), SecurityLevel.B2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, false), SecurityLevel.B1),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, true), SecurityLevel.B2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, false), SecurityLevel.B2),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, true), SecurityLevel.V3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, false), SecurityLevel.E2),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, true), SecurityLevel.V3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, false), SecurityLevel.V3)
    )),
    BIOMETRIC("Биометрические", Map.ofEntries(
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, true), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, false), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, true), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, false), SecurityLevel.A1),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, true), SecurityLevel.V2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, false), SecurityLevel.V2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, true), SecurityLevel.V2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, false), SecurityLevel.V2),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, true), SecurityLevel.G3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, false), SecurityLevel.G3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, true), SecurityLevel.G3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, false), SecurityLevel.G3)
    )),
    PUBLIC("Общедоступные", Map.ofEntries(
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, true), SecurityLevel.A2),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.GT, false), SecurityLevel.A2),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, true), SecurityLevel.A2),
            entry(new LevelKey(ThreatLevel.ONE, SubjectVolume.LT, false), SecurityLevel.A2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, true), SecurityLevel.A3),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.GT, false), SecurityLevel.G2),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, true), SecurityLevel.A3),
            entry(new LevelKey(ThreatLevel.TWO, SubjectVolume.LT, false), SecurityLevel.A3),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, true), SecurityLevel.A4),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.GT, false), SecurityLevel.A4),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, true), SecurityLevel.A4),
            entry(new LevelKey(ThreatLevel.THREE, SubjectVolume.LT, false), SecurityLevel.A4)
    ));

    @Getter
    private final String displayName;
    private final Map<LevelKey, SecurityLevel> levelMap;

    PDType(String displayName, Map<LevelKey, SecurityLevel> levelMap) {
        this.displayName = displayName;
        this.levelMap = levelMap;
    }

    public SecurityLevel getLevel(ThreatLevel threat, SubjectVolume volume, boolean hasEmployee) {
        return levelMap.get(new LevelKey(threat, volume, hasEmployee));
    }

    public static PDType fromDisplayName(String name) {
        return Stream.of(values())
                .filter(e -> e.displayName.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown PD type: " + name));
    }
}
