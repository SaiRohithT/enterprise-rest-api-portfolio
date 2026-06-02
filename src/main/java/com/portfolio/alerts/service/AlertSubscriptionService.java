package com.portfolio.alerts.service;

import com.portfolio.alerts.dto.AlertSubscriptionRequest;
import com.portfolio.alerts.dto.AlertSubscriptionResponse;
import com.portfolio.alerts.dto.AlertSubscriptionUpdateRequest;
import com.portfolio.alerts.entity.AlertSubscription;
import com.portfolio.alerts.entity.AlertType;
import com.portfolio.alerts.entity.Customer;
import com.portfolio.alerts.entity.DeliveryChannel;
import com.portfolio.alerts.entity.SubscriptionStatus;
import com.portfolio.alerts.exception.BadRequestException;
import com.portfolio.alerts.exception.DuplicateSubscriptionException;
import com.portfolio.alerts.exception.ResourceNotFoundException;
import com.portfolio.alerts.repository.AlertSubscriptionRepository;
import com.portfolio.alerts.repository.AlertTypeRepository;
import com.portfolio.alerts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing alert subscriptions.
 */
@Service
@RequiredArgsConstructor
public class AlertSubscriptionService {

    private final AlertSubscriptionRepository alertSubscriptionRepository;
    private final CustomerRepository customerRepository;
    private final AlertTypeRepository alertTypeRepository;

    /**
     * Get all subscriptions for a customer.
     */
    public List<AlertSubscriptionResponse> getCustomerSubscriptions(String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        return alertSubscriptionRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Create a new subscription for a customer.
     */
    public AlertSubscriptionResponse createSubscription(String customerId, AlertSubscriptionRequest request) {
        // Validate customer exists
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        // Validate alert type exists
        AlertType alertType = alertTypeRepository.findByAlertTypeCode(request.getAlertTypeCode())
                .orElseThrow(() -> new ResourceNotFoundException("Alert type not found: " + request.getAlertTypeCode()));

        // Validate delivery channel
        try {
            DeliveryChannel.valueOf(request.getDeliveryChannel());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid delivery channel: " + request.getDeliveryChannel());
        }

        // Check for duplicate active subscription
        boolean existingActive = alertSubscriptionRepository
                .existsByCustomerCustomerIdAndAlertTypeAlertTypeCodeAndSubscriptionStatus(
                        customerId, request.getAlertTypeCode(), SubscriptionStatus.ACTIVE);

        if (existingActive) {
            throw new DuplicateSubscriptionException(
                    "Active subscription already exists for this customer and alert type");
        }

        AlertSubscription subscription = AlertSubscription.builder()
                .customer(customer)
                .alertType(alertType)
                .deliveryChannel(DeliveryChannel.valueOf(request.getDeliveryChannel()))
                .subscriptionStatus(SubscriptionStatus.ACTIVE)
                .build();

        AlertSubscription saved = alertSubscriptionRepository.save(subscription);
        return mapToResponse(saved);
    }

    /**
     * Update an existing subscription.
     */
    public AlertSubscriptionResponse updateSubscription(
            String customerId, Long subscriptionId, AlertSubscriptionUpdateRequest request) {

        // Verify customer exists
        customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        // Verify subscription exists and belongs to customer
        AlertSubscription subscription = alertSubscriptionRepository
                .findByCustomerCustomerIdAndId(customerId, subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found: " + subscriptionId));

        // Validate delivery channel
        try {
            DeliveryChannel.valueOf(request.getDeliveryChannel());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid delivery channel: " + request.getDeliveryChannel());
        }

        // Validate subscription status
        try {
            SubscriptionStatus.valueOf(request.getSubscriptionStatus());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid subscription status: " + request.getSubscriptionStatus());
        }

        subscription.setDeliveryChannel(DeliveryChannel.valueOf(request.getDeliveryChannel()));
        subscription.setSubscriptionStatus(SubscriptionStatus.valueOf(request.getSubscriptionStatus()));

        AlertSubscription updated = alertSubscriptionRepository.save(subscription);
        return mapToResponse(updated);
    }

    /**
     * Delete a subscription by marking it as CANCELLED.
     */
    public void deleteSubscription(String customerId, Long subscriptionId) {
        // Verify customer exists
        customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        // Verify subscription exists and belongs to customer
        AlertSubscription subscription = alertSubscriptionRepository
                .findByCustomerCustomerIdAndId(customerId, subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found: " + subscriptionId));

        subscription.setSubscriptionStatus(SubscriptionStatus.CANCELLED);
        alertSubscriptionRepository.save(subscription);
    }

    /**
     * Map AlertSubscription entity to response DTO.
     */
    private AlertSubscriptionResponse mapToResponse(AlertSubscription subscription) {
        return AlertSubscriptionResponse.builder()
                .subscriptionId(subscription.getId())
                .customerId(subscription.getCustomer().getCustomerId())
                .alertTypeCode(subscription.getAlertType().getAlertTypeCode())
                .alertTypeName(subscription.getAlertType().getAlertTypeName())
                .deliveryChannel(subscription.getDeliveryChannel().toString())
                .subscriptionStatus(subscription.getSubscriptionStatus().toString())
                .createdAt(subscription.getCreatedAt())
                .updatedAt(subscription.getUpdatedAt())
                .build();
    }
}
