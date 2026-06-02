package com.portfolio.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for alert type response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertTypeResponse {
    private String alertTypeCode;
    private String alertTypeName;
    private String description;
    private String category;
    private Boolean active;
}
