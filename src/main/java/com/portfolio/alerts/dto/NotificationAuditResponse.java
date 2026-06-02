package com.portfolio.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for notification audit response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationAuditResponse {
    private String transactionId;
    private String customerId;
    private String alertTypeCode;
    private String alertTypeName;
    private String deliveryChannel;
    private String notificationStatus;
    private String message;
    private String failureReason;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}
