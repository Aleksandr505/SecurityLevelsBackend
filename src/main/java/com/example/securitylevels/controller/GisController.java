package com.example.securitylevels.controller;

import com.example.securitylevels.model.domain.gis.GisMeasures;
import com.example.securitylevels.model.domain.gis.SecurityClassTable;
import com.example.securitylevels.model.dto.GisRequest;
import com.example.securitylevels.model.dto.GisResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gis")
public class GisController {

    @PostMapping
    public GisResponse handleGISForm(
            @Valid
            @RequestBody
            GisRequest gisRequest
    ) {

        // 1. Получение класса защищённости
        int protectClass = SecurityClassTable.getByClassNumber(gisRequest.getLevel()).getLevelForSystem(gisRequest.getScale());

        // 2. Фильтрация мер по классу
        Map<String, List<GisMeasures.Measure>> filtered = new LinkedHashMap<>();
        for (Map.Entry<String, List<GisMeasures.Measure>> entry : GisMeasures.MEASURES.entrySet()) {
            List<GisMeasures.Measure> suitable = entry.getValue().stream()
                    .filter(m -> m.levels.contains(protectClass))
                    .toList();

            if (!suitable.isEmpty()) {
                filtered.put(entry.getKey(), suitable);
            }
        }


        return new GisResponse(protectClass, filtered);
    }

}
