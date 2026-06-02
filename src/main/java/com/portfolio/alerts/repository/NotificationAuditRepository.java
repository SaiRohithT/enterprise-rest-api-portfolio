package com.portfolio.alerts.repository;

import com.portfolio.alerts.entity.NotificationAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for NotificationAudit entity providing database access operations.
 */
@Repository
public interface NotificationAuditRepository extends JpaRepository<NotificationAudit, Long> {
    List<NotificationAudit> findByCustomerCustomerIdOrderByCreatedAtDesc(String customerId);
}
