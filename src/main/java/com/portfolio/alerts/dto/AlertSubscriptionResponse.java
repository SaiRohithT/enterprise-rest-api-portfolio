package com.portfolio.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for alert subscription response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertSubscriptionResponse {
    private Long subscriptionId;
    private String customerId;
    private String alertTypeCode;
    private String alertTypeName;
    private String deliveryChannel;
    private String subscriptionStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
