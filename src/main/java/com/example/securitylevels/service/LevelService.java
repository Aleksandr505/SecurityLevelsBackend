package com.example.securitylevels.service;

import com.example.securitylevels.model.domain.PDType;
import com.example.securitylevels.model.domain.SecurityLevel;
import com.example.securitylevels.model.domain.SubjectVolume;
import com.example.securitylevels.model.domain.ThreatLevel;
import com.example.securitylevels.model.dto.LevelRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class LevelService {
    /**
     * Рассчитывает наиболее строгий уровень защиты на основе параметров запроса
     * @param request DTO с исходными данными из клиента
     * @return SecurityLevel (enum) с кодом и пояснением
     */
    public SecurityLevel calculateMaxSecurityLevel(LevelRequest request) {
        // 1. Определяем уровень угроз:
        //    - Если ОС не сертифицирована → I тип угроз
        //    - Иначе, если ПО не сертифицировано → II тип угроз
        //    - Иначе → III тип угроз
        ThreatLevel threatLevel = ThreatLevel.THREE;
        if (!"certified".equals(request.getCertApp())) {
            threatLevel = ThreatLevel.TWO;
        }
        if (!"certified".equals(request.getCertOs())) {
            threatLevel = ThreatLevel.ONE;
        }

        // 2. Определяем объём субъектов персональных данных.
        //    gt → более 100 тысяч, lt → менее 100 тысяч
        SubjectVolume volume = "gt".equals(request.getNumber())
                ? SubjectVolume.GT
                : SubjectVolume.LT;

        // 3. Для каждого выбранного типа ПД получаем соответствующий уровень защиты
        ThreatLevel finalThreatLevel = threatLevel;
        List<SecurityLevel> levels = request.getSelectedOptions().stream()
                // Преобразуем строку к enum PDType
                .map(PDType::fromDisplayName)
                // Из таблицы PDType получаем нужный SecurityLevel
                .map(pdType -> pdType.getLevel(finalThreatLevel, volume, request.getEmployee()))
                .toList();

        // 4. Выбираем наиболее строгий уровень (минимальный по приоритету)
        return levels.stream()
                .min(Comparator.comparingInt(SecurityLevel::getPriority))
                .orElseThrow(() -> new IllegalStateException(
                        "Не удалось определить уровень безопасности"));
    }
}
