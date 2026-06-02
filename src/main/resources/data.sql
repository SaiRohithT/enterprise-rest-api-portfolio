-- Sample data for local development
-- Customer Alert Management Platform

-- Insert Alert Types
INSERT INTO alert_types (alert_type_code, alert_type_name, description, category, active, created_at, updated_at) VALUES
('LOW_BALANCE', 'Low Balance Alert', 'Notification when account balance falls below threshold', 'Account', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('LARGE_TRANSACTION', 'Large Transaction Alert', 'Notification for transactions above configured limit', 'Transaction', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PAYMENT_DUE', 'Payment Due Alert', 'Reminder for upcoming bill or loan payment', 'Payment', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CARD_TRANSACTION', 'Card Transaction Alert', 'Real-time notification for card purchases', 'Transaction', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('LOGIN_ALERT', 'Login Alert', 'Notification for new account login from unknown device', 'Security', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Customers
INSERT INTO customers (customer_id, first_name, last_name, email, phone_number, status, created_at, updated_at) VALUES
('CUST001', 'John', 'Anderson', 'john.anderson@example.com', '+1-555-0101', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CUST002', 'Sarah', 'Mitchell', 'sarah.mitchell@example.com', '+1-555-0102', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CUST003', 'Michael', 'Chen', 'michael.chen@example.com', '+1-555-0103', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CUST004', 'Emily', 'Rodriguez', 'emily.rodriguez@example.com', '+1-555-0104', 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CUST005', 'Robert', 'Johnson', 'robert.johnson@example.com', '+1-555-0105', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Contact Preferences for CUST001
INSERT INTO contact_preferences (customer_id, delivery_channel, contact_value, primary_preference, verified, created_at, updated_at) VALUES
(1, 'EMAIL', 'john.anderson@example.com', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'SMS', '+1-555-0101', false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'PUSH', 'john.anderson.mobile@device.id', false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Contact Preferences for CUST002
INSERT INTO contact_preferences (customer_id, delivery_channel, contact_value, primary_preference, verified, created_at, updated_at) VALUES
(2, 'EMAIL', 'sarah.mitchell@example.com', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'SMS', '+1-555-0102', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Contact Preferences for CUST003
INSERT INTO contact_preferences (customer_id, delivery_channel, contact_value, primary_preference, verified, created_at, updated_at) VALUES
(3, 'EMAIL', 'michael.chen@example.com', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'PUSH', 'michael.chen.mobile@device.id', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Contact Preferences for CUST005
INSERT INTO contact_preferences (customer_id, delivery_channel, contact_value, primary_preference, verified, created_at, updated_at) VALUES
(5, 'EMAIL', 'robert.johnson@example.com', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'SMS', '+1-555-0105', false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Alert Subscriptions for CUST001
INSERT INTO alert_subscriptions (customer_id, alert_type_id, delivery_channel, subscription_status, created_at, updated_at) VALUES
(1, 1, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, 'SMS', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 3, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 4, 'PUSH', 'SUSPENDED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Alert Subscriptions for CUST002
INSERT INTO alert_subscriptions (customer_id, alert_type_id, delivery_channel, subscription_status, created_at, updated_at) VALUES
(2, 1, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 4, 'SMS', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 5, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Alert Subscriptions for CUST003
INSERT INTO alert_subscriptions (customer_id, alert_type_id, delivery_channel, subscription_status, created_at, updated_at) VALUES
(3, 1, 'SMS', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 2, 'EMAIL', 'SUSPENDED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 4, 'PUSH', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 5, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Alert Subscriptions for CUST005
INSERT INTO alert_subscriptions (customer_id, alert_type_id, delivery_channel, subscription_status, created_at, updated_at) VALUES
(5, 1, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 4, 'SMS', 'CANCELLED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 5, 'EMAIL', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Notification Audit Records for CUST001
INSERT INTO notification_audit (customer_id, alert_type_id, delivery_channel, notification_status, transaction_id, message, failure_reason, sent_at, created_at) VALUES
(1, 1, 'EMAIL', 'SENT', 'TXN-2026-06-02-001', 'Your account balance is below $1000', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, 'SMS', 'SENT', 'TXN-2026-06-02-002', 'Transaction of $5000 detected on your account', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 3, 'EMAIL', 'PENDING', 'TXN-2026-06-02-003', 'Your credit card payment is due on 2026-06-15', NULL, NULL, CURRENT_TIMESTAMP),
(1, 4, 'PUSH', 'FAILED', 'TXN-2026-06-02-004', 'Card purchase of $250 at RetailCo', 'Device offline', NULL, CURRENT_TIMESTAMP);

-- Insert Notification Audit Records for CUST002
INSERT INTO notification_audit (customer_id, alert_type_id, delivery_channel, notification_status, transaction_id, message, failure_reason, sent_at, created_at) VALUES
(2, 1, 'EMAIL', 'SENT', 'TXN-2026-06-02-005', 'Your savings account balance is low', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 4, 'SMS', 'SENT', 'TXN-2026-06-02-006', 'Debit card transaction: $75.50 at GasStation', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 5, 'EMAIL', 'SENT', 'TXN-2026-06-02-007', 'New login detected from IP 192.168.1.100', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Notification Audit Records for CUST003
INSERT INTO notification_audit (customer_id, alert_type_id, delivery_channel, notification_status, transaction_id, message, failure_reason, sent_at, created_at) VALUES
(3, 1, 'SMS', 'SENT', 'TXN-2026-06-02-008', 'Balance alert: $500 remaining', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, 'EMAIL', 'SENT', 'TXN-2026-06-02-009', 'Loan payment due on 2026-06-10', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 4, 'PUSH', 'PENDING', 'TXN-2026-06-02-010', 'Purchase notification', NULL, NULL, CURRENT_TIMESTAMP);

-- Insert Notification Audit Records for CUST005
INSERT INTO notification_audit (customer_id, alert_type_id, delivery_channel, notification_status, transaction_id, message, failure_reason, sent_at, created_at) VALUES
(5, 1, 'EMAIL', 'SENT', 'TXN-2026-06-02-011', 'Your checking account balance is $2500', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 5, 'EMAIL', 'SENT', 'TXN-2026-06-02-012', 'Login attempt from new location: London', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 3, 'EMAIL', 'FAILED', 'TXN-2026-06-02-013', 'Your mortgage payment is due', 'Email server timeout', NULL, CURRENT_TIMESTAMP);
