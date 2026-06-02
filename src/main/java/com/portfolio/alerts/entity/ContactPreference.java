package com.portfolio.alerts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * ContactPreference entity representing customer's contact preferences for different delivery channels.
 */
@Entity
@Table(name = "contact_preferences", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"customer_id", "delivery_channel"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "delivery_channel", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryChannel deliveryChannel;

    @Column(name = "contact_value", nullable = false)
    private String contactValue;

    @Column(name = "primary_preference", nullable = false)
    private Boolean primaryPreference;

    @Column(name = "verified", nullable = false)
    private Boolean verified;

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
