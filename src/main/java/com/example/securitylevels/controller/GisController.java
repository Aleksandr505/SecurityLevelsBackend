package com.example.securitylevels.controller;

import com.example.securitylevels.model.domain.gis.SecurityClassTable;
import com.example.securitylevels.model.dto.GisRequest;
import com.example.securitylevels.model.dto.GisResponse;
import com.example.securitylevels.service.GisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gis")
public class GisController {

    public GisController(GisService gisService) {
        this.gisService = gisService;
    }

    private final GisService gisService;

    /**
     * Обрабатывает POST-запрос от фронтенда и возвращает JSON с результатом.
     * Пример тела запроса:
     * {
     *   "level": "2",
     *   "scale": "FEDERAL"
     * }
     *
     * @param gisRequest DTO с проверенными полями (валидация через @Valid)
     * @return GisResponse
     */
    @PostMapping
    public GisResponse handleGISForm(
            @Valid
            @RequestBody
            GisRequest gisRequest
    ) {
        // 1. Получение класса защищённости
        int protectClass = SecurityClassTable.getByClassNumber(gisRequest.getLevel()).getLevelForSystem(gisRequest.getScale());

        // 2. Фильтрация мер по классу
        var filteredMeasures = gisService.filterMeasures(protectClass);

        return new GisResponse(protectClass, filteredMeasures);
    }

}
