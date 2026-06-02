package com.portfolio.alerts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * AlertSubscription entity representing a customer's subscription to a specific alert type.
 */
@Entity
@Table(name = "alert_subscriptions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"customer_id", "alert_type_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alert_type_id", nullable = false)
    private AlertType alertType;

    @Column(name = "delivery_channel", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryChannel deliveryChannel;

    @Column(name = "subscription_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

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
