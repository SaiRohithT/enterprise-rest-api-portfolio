package com.portfolio.alerts.service;

import com.portfolio.alerts.dto.NotificationAuditResponse;
import com.portfolio.alerts.repository.CustomerRepository;
import com.portfolio.alerts.repository.NotificationAuditRepository;
import com.portfolio.alerts.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing notification audit records.
 */
@Service
@RequiredArgsConstructor
public class NotificationAuditService {

    private final NotificationAuditRepository notificationAuditRepository;
    private final CustomerRepository customerRepository;

    /**
     * Get notification audit records for a customer.
     */
    public List<NotificationAuditResponse> getCustomerNotificationAudit(String customerId) {
        // Verify customer exists
        customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        return notificationAuditRepository.findByCustomerCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(audit -> NotificationAuditResponse.builder()
                        .transactionId(audit.getTransactionId())
                        .customerId(audit.getCustomer().getCustomerId())
                        .alertTypeCode(audit.getAlertType().getAlertTypeCode())
                        .alertTypeName(audit.getAlertType().getAlertTypeName())
                        .deliveryChannel(audit.getDeliveryChannel().toString())
                        .notificationStatus(audit.getNotificationStatus().toString())
                        .message(audit.getMessage())
                        .failureReason(audit.getFailureReason())
                        .sentAt(audit.getSentAt())
                        .createdAt(audit.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
