package com.portfolio.alerts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating alert subscription request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertSubscriptionRequest {
    
    @NotBlank(message = "Alert type code is required")
    private String alertTypeCode;
    
    @NotBlank(message = "Delivery channel is required")
    private String deliveryChannel;
}
