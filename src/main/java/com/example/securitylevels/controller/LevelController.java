package com.example.securitylevels.controller;

import com.example.securitylevels.model.domain.SecurityLevel;
import com.example.securitylevels.model.dto.LevelRequest;
import com.example.securitylevels.model.dto.LevelResponse;
import com.example.securitylevels.service.GisService;
import com.example.securitylevels.service.LevelService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для определения уровня защиты персональных данных.
 */
@RestController
@RequestMapping("/api/level")
public class LevelController {

    private final LevelService levelService;
    private final GisService gisService;

    public LevelController(LevelService levelService, GisService gisService) {
        this.levelService = levelService;
        this.gisService = gisService;
    }

    /**
     * Обрабатывает POST-запрос от фронтенда и возвращает JSON с результатом.
     * Пример тела запроса:
     * {
     *   "certOs": "certified",
     *   "certApp": "not-certified",
     *   "network": "network",
     *   "number": "gt",
     *   "selectedOptions": ["Иные", "Биометрические"],
     *   "isEmployee": "false"
     * }
     *
     * @param request DTO с проверенными полями (валидация через @Valid)
     * @return ResponseEntity с телом LevelResponse
     */
    @PostMapping
    public ResponseEntity<LevelResponse> getLevel(@Valid @RequestBody LevelRequest request) {
        LevelResponse response;
        try {
            // Вычисляем уровень через сервис
            SecurityLevel securityLevel = levelService.calculateMaxSecurityLevel(request);

            // Фильтрация мер по классу
            var measures = gisService.filterMeasures(securityLevel.getPriority());

            // Формируем ответ
            response = new LevelResponse(
                    securityLevel.getCode(),      // код уровня, например "1а"
                    securityLevel.getReason(),     // описание причины
                    measures
            );
        } catch (Exception e) {
            // Ловим ошибки со стороны сервера
            response = new LevelResponse(null, e.getMessage(), null);
            return ResponseEntity
                    .status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }

        // Возвращаем ответ
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
