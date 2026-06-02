package com.portfolio.alerts.repository;

import com.portfolio.alerts.entity.AlertSubscription;
import com.portfolio.alerts.entity.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for AlertSubscription entity providing database access operations.
 */
@Repository
public interface AlertSubscriptionRepository extends JpaRepository<AlertSubscription, Long> {
    List<AlertSubscription> findByCustomerCustomerId(String customerId);
    Optional<AlertSubscription> findByCustomerCustomerIdAndId(String customerId, Long id);
    boolean existsByCustomerCustomerIdAndAlertTypeAlertTypeCodeAndSubscriptionStatus(
        String customerId, String alertTypeCode, SubscriptionStatus subscriptionStatus);
}
