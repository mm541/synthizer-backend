# Synthizer Backend

Production-style Spring Boot backend built with Java 21 and Maven.

## Stack

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- Bean Validation
- PostgreSQL
- Flyway
- Actuator
- Lombok
- Maven

## Current Structure

```text
src/main/java/com/moa/synthizerbackend
├── auth
├── common
├── health
├── notebook
└── user
```

This repository currently contains the base project scaffold and modular package structure. Business logic is intentionally not implemented yet.

## Run Locally

```bash
./mvnw spring-boot:run
```

## Build

```bash
./mvnw clean compile
```

## Test

```bash
./mvnw test
```

Note: the default Spring context test will require datasource configuration before full test execution succeeds.

## Planned Additions

- JWT authentication
- RabbitMQ integration
- File upload support
- Production configuration profiles
- API documentation
