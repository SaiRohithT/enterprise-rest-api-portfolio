package com.portfolio.alerts.controller;

import com.portfolio.alerts.dto.AlertTypeResponse;
import com.portfolio.alerts.service.AlertTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * REST controller for managing alert types.
 */
@RestController
@RequestMapping("/api/v1/alert-types")
@RequiredArgsConstructor
public class AlertTypeController {

    private final AlertTypeService alertTypeService;

    /**
     * Get all active alert types.
     */
    @GetMapping
    public ResponseEntity<List<AlertTypeResponse>> getActiveAlertTypes() {
        List<AlertTypeResponse> alertTypes = alertTypeService.getActiveAlertTypes();
        return ResponseEntity.ok(alertTypes);
    }
}
