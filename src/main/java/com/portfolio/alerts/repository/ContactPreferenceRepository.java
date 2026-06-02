package com.portfolio.alerts.repository;

import com.portfolio.alerts.entity.ContactPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ContactPreference entity providing database access operations.
 */
@Repository
public interface ContactPreferenceRepository extends JpaRepository<ContactPreference, Long> {
}
