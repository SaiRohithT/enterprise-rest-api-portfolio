package com.portfolio.alerts.service;

import com.portfolio.alerts.dto.AlertTypeResponse;
import com.portfolio.alerts.repository.AlertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing alert types.
 */
@Service
@RequiredArgsConstructor
public class AlertTypeService {

    private final AlertTypeRepository alertTypeRepository;

    /**
     * Get all active alert types.
     */
    public List<AlertTypeResponse> getActiveAlertTypes() {
        return alertTypeRepository.findByActiveTrue()
                .stream()
                .map(alertType -> AlertTypeResponse.builder()
                        .alertTypeCode(alertType.getAlertTypeCode())
                        .alertTypeName(alertType.getAlertTypeName())
                        .description(alertType.getDescription())
                        .category(alertType.getCategory())
                        .active(alertType.getActive())
                        .build())
                .collect(Collectors.toList());
    }
}
