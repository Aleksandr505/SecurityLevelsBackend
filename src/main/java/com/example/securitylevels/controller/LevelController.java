package com.example.securitylevels.controller;

import com.example.securitylevels.model.domain.SecurityLevel;
import com.example.securitylevels.model.dto.LevelRequest;
import com.example.securitylevels.model.dto.LevelResponse;
import com.example.securitylevels.service.LevelService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для определения уровня защиты персональных данных.
 */
@RestController
@RequestMapping("/api/level")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    /**
     * Обрабатывает POST-запрос от фронтенда и возвращает JSON с результатом.
     * Пример тела запроса:
     * {
     *   "certOs": "certified",
     *   "certApp": "not-certified",
     *   "network": "network",
     *   "number": "gt",
     *   "selectedOptions": ["Иные", "Биометрические"]
     * }
     *
     * @param request DTO с проверенными полями (валидация через @Valid)
     * @return ResponseEntity с телом LevelResponse или 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<LevelResponse> getLevel(@Valid @RequestBody LevelRequest request) {
        // Проверка, что хотя бы одна категория ПД выбрана
        if (request.getSelectedOptions().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new LevelResponse(null, "Ошибка: выберите хотя бы одну категорию ПД")
            );
        }

        LevelResponse response;
        try {
            // Вычисляем уровень через сервис
            SecurityLevel securityLevel = levelService.calculateMaxSecurityLevel(request);
            // Формируем ответ
            response = new LevelResponse(
                    securityLevel.getCode(),      // код уровня, например "1а"
                    securityLevel.getReason()     // описание причины
            );
        } catch (Exception e) {
            response = new LevelResponse(null, "Внутренняя ошибка сервера");
            return ResponseEntity
                    .status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
