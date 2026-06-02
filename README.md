# Customer Alert Management Platform

A portfolio project demonstrating enterprise-grade REST API development with Spring Boot 3.x.

## Purpose

This project showcases a scalable customer alert management system with core features for managing customer alerts, notifications, and preferences in a microservices-ready architecture.

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 3.3.0
- **Build Tool**: Maven
- **Database**: H2 (Development), PostgreSQL (Production)
- **API Documentation**: Springdoc OpenAPI (Swagger)
- **Testing**: JUnit 5, Mockito

## How to Run Locally

### Prerequisites
- Java 17 or higher
- Maven 3.8+

### Build and Run

```bash
# Clone the repository
git clone <repo-url>
cd customer-alert-management

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Run tests
mvn test
```

### Access the Application

- Application: `http://localhost:8080`
- API Documentation: `http://localhost:8080/swagger-ui.html`
- Health Check: `http://localhost:8080/actuator/health`

### Application Profiles

- `local`: Uses H2 in-memory database (default)
- `prod`: Uses PostgreSQL database

Run with specific profile:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

## API Endpoints

### Alert Types
- **GET** `/api/v1/alert-types` - Get all active alert types

### Alert Subscriptions
- **GET** `/api/v1/customers/{customerId}/subscriptions` - Get customer subscriptions
- **POST** `/api/v1/customers/{customerId}/subscriptions` - Create new subscription
- **PUT** `/api/v1/customers/{customerId}/subscriptions/{subscriptionId}` - Update subscription
- **DELETE** `/api/v1/customers/{customerId}/subscriptions/{subscriptionId}` - Delete subscription

### Notification Audit
- **GET** `/api/v1/customers/{customerId}/notification-audit` - Get notification audit records

## Project Structure

```
src/main/java/com/portfolio/alerts/
├── controller/       - REST API endpoints
├── service/          - Business logic layer
├── repository/       - Data access layer (Spring Data JPA)
├── entity/           - JPA entities and enums
├── dto/              - Data transfer objects
├── exception/        - Custom exceptions and global error handler
├── config/           - Spring configuration
└── AlertManagementApplication.java - Main entry point

src/main/resources/
├── application.yml - Default configuration
├── application-local.yml - Local development profile
└── data.sql - Sample data initialization
```

## Key Features

- **RESTful API Design** - Following REST conventions
- **Input Validation** - Using Jakarta validation annotations
- **Global Exception Handling** - Centralized error response management
- **Service Layer** - Business logic encapsulation
- **Database Integration** - JPA/Hibernate with relationships
- **Sample Data** - Pre-loaded banking-style alerts and customers
- **API Documentation** - Swagger UI with Springdoc OpenAPI

## Banking Alert Types

- **LOW_BALANCE** - Account balance below threshold
- **LARGE_TRANSACTION** - Transactions above configured limit
- **PAYMENT_DUE** - Upcoming bill/loan payment reminders
- **CARD_TRANSACTION** - Real-time card purchase notifications
- **LOGIN_ALERT** - New login from unknown device

## Delivery Channels

- **EMAIL** - Email notifications
- **SMS** - Text message notifications
- **PUSH** - Mobile push notifications

## Subscription Status

- **ACTIVE** - Subscription is active
- **SUSPENDED** - Subscription is suspended
- **CANCELLED** - Subscription is cancelled (soft delete)
