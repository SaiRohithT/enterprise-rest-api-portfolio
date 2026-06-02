package com.portfolio.alerts.repository;

import com.portfolio.alerts.entity.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for AlertType entity providing database access operations.
 */
@Repository
public interface AlertTypeRepository extends JpaRepository<AlertType, Long> {
    Optional<AlertType> findByAlertTypeCode(String alertTypeCode);
    List<AlertType> findByActiveTrue();
}
