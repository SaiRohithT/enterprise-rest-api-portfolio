package com.portfolio.alerts.controller;

import com.portfolio.alerts.dto.NotificationAuditResponse;
import com.portfolio.alerts.service.NotificationAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * REST controller for managing notification audit records.
 */
@RestController
@RequestMapping("/api/v1/customers/{customerId}/notification-audit")
@RequiredArgsConstructor
public class NotificationAuditController {

    private final NotificationAuditService notificationAuditService;

    /**
     * Get notification audit records for a customer.
     */
    @GetMapping
    public ResponseEntity<List<NotificationAuditResponse>> getCustomerNotificationAudit(
            @PathVariable String customerId) {
        List<NotificationAuditResponse> auditRecords = 
                notificationAuditService.getCustomerNotificationAudit(customerId);
        return ResponseEntity.ok(auditRecords);
    }
}
