package com.portfolio.alerts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * AlertType entity representing different types of alerts available in the system.
 */
@Entity
@Table(name = "alert_types", uniqueConstraints = {
    @UniqueConstraint(columnNames = "alert_type_code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alert_type_code", nullable = false, unique = true)
    private String alertTypeCode;

    @Column(name = "alert_type_name", nullable = false)
    private String alertTypeName;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
