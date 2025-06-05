package com.example.securitylevels.service;

import com.example.securitylevels.model.domain.gis.GisMeasures;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GisService {

    public Map<String, List<GisMeasures.Measure>> filterMeasures(int protectClass) {
        // Фильтрация мер по классу защиты
        Map<String, List<GisMeasures.Measure>> filtered = new LinkedHashMap<>();
        for (Map.Entry<String, List<GisMeasures.Measure>> entry : GisMeasures.MEASURES.entrySet()) {
            List<GisMeasures.Measure> suitable = entry.getValue().stream()
                    .filter(m -> m.levels.contains(protectClass))
                    .toList();

            if (!suitable.isEmpty()) {
                filtered.put(entry.getKey(), suitable);
            }
        }
        return filtered;
    }

}
