package com.example.securitylevels.model.domain.gis;

import java.util.Map;

public enum SecurityClassTable {
    CLASS_1(1, Map.of(
            ScaleLevel.FEDERAL, 1,
            ScaleLevel.REGIONAL, 1,
            ScaleLevel.OBJECT, 1
    )),
    CLASS_2(2, Map.of(
            ScaleLevel.FEDERAL, 1,
            ScaleLevel.REGIONAL, 2,
            ScaleLevel.OBJECT, 2
    )),
    CLASS_3(3, Map.of(
            ScaleLevel.FEDERAL, 2,
            ScaleLevel.REGIONAL, 3,
            ScaleLevel.OBJECT, 3
    ));

    private final int classNumber;
    private final Map<ScaleLevel, Integer> levelMapping;

    SecurityClassTable(int classNumber, Map<ScaleLevel, Integer> levelMapping) {
        this.classNumber = classNumber;
        this.levelMapping = levelMapping;
    }

    public static SecurityClassTable getByClassNumber(int classNumber) {
        return switch (classNumber) {
            case 1 -> CLASS_1;
            case 2 -> CLASS_2;
            case 3 -> CLASS_3;
            default -> throw new IllegalArgumentException("Invalid class number: " + classNumber);
        };
    }

    public int getLevelForSystem(ScaleLevel level) {
        return levelMapping.get(level);
    }
}
