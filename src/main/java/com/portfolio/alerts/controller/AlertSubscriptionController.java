package com.portfolio.alerts.controller;

import com.portfolio.alerts.dto.AlertSubscriptionRequest;
import com.portfolio.alerts.dto.AlertSubscriptionResponse;
import com.portfolio.alerts.dto.AlertSubscriptionUpdateRequest;
import com.portfolio.alerts.service.AlertSubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing alert subscriptions.
 */
@RestController
@RequestMapping("/api/v1/customers/{customerId}/subscriptions")
@RequiredArgsConstructor
public class AlertSubscriptionController {

    private final AlertSubscriptionService alertSubscriptionService;

    /**
     * Get all subscriptions for a customer.
     */
    @GetMapping
    public ResponseEntity<List<AlertSubscriptionResponse>> getCustomerSubscriptions(
            @PathVariable String customerId) {
        List<AlertSubscriptionResponse> subscriptions = 
                alertSubscriptionService.getCustomerSubscriptions(customerId);
        return ResponseEntity.ok(subscriptions);
    }

    /**
     * Create a new subscription for a customer.
     */
    @PostMapping
    public ResponseEntity<AlertSubscriptionResponse> createSubscription(
            @PathVariable String customerId,
            @Valid @RequestBody AlertSubscriptionRequest request) {
        AlertSubscriptionResponse response = 
                alertSubscriptionService.createSubscription(customerId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update an existing subscription.
     */
    @PutMapping("/{subscriptionId}")
    public ResponseEntity<AlertSubscriptionResponse> updateSubscription(
            @PathVariable String customerId,
            @PathVariable Long subscriptionId,
            @Valid @RequestBody AlertSubscriptionUpdateRequest request) {
        AlertSubscriptionResponse response = 
                alertSubscriptionService.updateSubscription(customerId, subscriptionId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a subscription by marking it as CANCELLED.
     */
    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable String customerId,
            @PathVariable Long subscriptionId) {
        alertSubscriptionService.deleteSubscription(customerId, subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
