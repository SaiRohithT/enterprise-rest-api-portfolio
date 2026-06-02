package com.portfolio.alerts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating alert subscription request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertSubscriptionUpdateRequest {
    
    @NotBlank(message = "Delivery channel is required")
    private String deliveryChannel;
    
    @NotBlank(message = "Subscription status is required")
    private String subscriptionStatus;
}
