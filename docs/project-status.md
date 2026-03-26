# Synthizer Backend Project Status

This document captures the project state up to now, including the setup decisions, code structure, configuration, runtime behavior, known issues, and Git history.

## 1. Project Overview

- Project name: `synthizer-backend`
- Group/artifact: `com.moa:synthizer-backend`
- Java version: `21`
- Build tool: `Maven`
- Base package: `com.moa.synthizerbackend`
- Repository: `https://github.com/mm541/synthizer-backend.git`
- Current branch: `main`

The project is currently a production-style Spring Boot backend scaffold. The modular package layout is in place, basic shared types are present, Docker-backed PostgreSQL is configured, Flyway is enabled, MapStruct is wired, and a basic health controller exists. Business logic and database tables are intentionally not implemented yet.

## 2. Technology Stack

- Spring Boot `3.5.12`
- Spring Web
- Spring Data JPA
- Spring Security
- Bean Validation
- Flyway
- Actuator
- PostgreSQL
- Lombok
- MapStruct
- Micrometer Prometheus registry
- Docker Compose for local PostgreSQL

## 3. Current Maven Configuration

The current `pom.xml` includes:

- `spring-boot-starter-actuator`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-validation`
- `spring-boot-starter-web`
- `flyway-core`
- `flyway-database-postgresql`
- `spring-boot-devtools`
- `micrometer-registry-prometheus`
- `postgresql`
- `lombok`
- `mapstruct`
- `spring-boot-starter-test`
- `spring-security-test`

MapStruct setup:

- `mapstruct.version=1.6.3`
- `lombok-mapstruct-binding.version=0.2.0`
- `maven-compiler-plugin` is configured with:
  - `mapstruct-processor`
  - `lombok`
  - `lombok-mapstruct-binding`
  - compiler argument: `-Amapstruct.defaultComponentModel=spring`

Observations:

- Compile passes with `./mvnw -q -DskipTests compile`.
- During one runtime compile pass, Maven printed `The following options were not recognized by any processor: '[mapstruct.defaultComponentModel]'`.
- No mapper implementations are defined yet, so MapStruct is configured but not really exercised yet.

## 4. Package Structure

Current package tree:

```text
com.moa.synthizerbackend
├── SynthizerBackendApplication
├── common
│   ├── config
│   ├── constants
│   ├── exception
│   ├── response
│   └── util
├── health
│   └── controller
├── auth
│   ├── controller
│   ├── dto
│   ├── jwt
│   ├── security
│   └── service
├── user
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── mapper
│   ├── repository
│   └── service
├── notebook
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── mapper
│   ├── repository
│   └── service
├── asset
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── mapper
│   ├── repository
│   └── service
└── generation
    ├── controller
    ├── dto
    ├── entity
    ├── mapper
    ├── repository
    └── service
```

## 5. Existing Java Files

Main application entry:

- `src/main/java/com/moa/synthizerbackend/SynthizerBackendApplication.java`

Shared/common:

- `common/config/ApplicationConfig.java`
- `common/constants/AppConstants.java`
- `common/exception/ApplicationException.java`
- `common/exception/GlobalExceptionHandler.java`
- `common/response/ApiResponse.java`
- `common/util/DateTimeUtils.java`

Health:

- `health/controller/HealthController.java`

Auth:

- `auth/controller/AuthController.java`
- `auth/dto/AuthResponse.java`
- `auth/dto/LoginRequest.java`
- `auth/dto/RegisterRequest.java`
- `auth/jwt/JwtService.java`
- `auth/security/SecurityConfig.java`
- `auth/service/AuthService.java`

User:

- `user/controller/UserController.java`
- `user/dto/UserRequest.java`
- `user/dto/UserResponse.java`
- `user/entity/User.java`
- `user/mapper/UserMapper.java`
- `user/repository/UserRepository.java`
- `user/service/UserService.java`

Notebook:

- `notebook/controller/NotebookController.java`
- `notebook/dto/NotebookRequest.java`
- `notebook/dto/NotebookResponse.java`
- `notebook/entity/Notebook.java`
- `notebook/mapper/NotebookMapper.java`
- `notebook/repository/NotebookRepository.java`
- `notebook/service/NotebookService.java`

Asset:

- `asset/controller/AssetController.java`
- `asset/dto/AssetRequest.java`
- `asset/dto/AssetResponse.java`
- `asset/entity/Asset.java`
- `asset/mapper/AssetMapper.java`
- `asset/repository/AssetRepository.java`
- `asset/service/AssetService.java`

Generation:

- `generation/controller/GenerationController.java`
- `generation/dto/GenerationRequest.java`
- `generation/dto/GenerationResponse.java`
- `generation/entity/Generation.java`
- `generation/mapper/GenerationMapper.java`
- `generation/repository/GenerationRepository.java`
- `generation/service/GenerationService.java`

Test:

- `src/test/java/com/moa/synthizerbackend/SynthizerBackendApplicationTests.java`

## 6. Current Placeholder Scope

The codebase is still mostly scaffold-level. The following are intentionally minimal:

- controllers mostly exist as placeholders
- services are interfaces without business methods
- mappers are empty interfaces
- repositories exist and extend `JpaRepository`
- entities exist with basic UUID-based IDs and placeholder fields
- no real service implementations or domain workflows are present

This is deliberate. The project has structure first, implementation later.

## 7. Application Configuration

Current `application.properties` contains:

- application name: `synthizer-backend`
- server port: `8080`
- PostgreSQL datasource:
  - URL: `jdbc:postgresql://localhost:5432/synthizer`
  - username: `postgres`
  - password: `postgres`
  - driver: `org.postgresql.Driver`
- JPA:
  - `spring.jpa.hibernate.ddl-auto=validate`
  - SQL logging enabled
  - SQL formatting enabled
- Flyway:
  - enabled
  - migration path: `classpath:db/migration`
- multipart upload:
  - max file size `100MB`
  - max request size `100MB`
- actuator exposure:
  - `health,info,metrics,prometheus`
- logging:
  - Spring Security `INFO`
  - Hibernate SQL `DEBUG`
  - Hibernate bind values `TRACE`

Cleanup already performed:

- explicit Hibernate PostgreSQL dialect was removed because Hibernate auto-detects it and warned it was unnecessary

## 8. Docker Setup

Local PostgreSQL is configured through `docker-compose.yml`.

Current container setup:

- image: `postgres:16`
- container name: `synthizer-postgres`
- database: `synthizer`
- username: `postgres`
- password: `postgres`
- port mapping: `5432:5432`
- volume: `synthizer_postgres_data`

Operational note:

- Docker Compose worked
- Docker printed a warning that the `version: '3.8'` line is obsolete with modern Compose
- the file still works, but that line can be removed later

## 9. Flyway State

Flyway is enabled and pointed at `src/main/resources/db/migration`.

Current migration set:

- `V1__init.sql`

Contents:

```sql
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
```

Current interpretation:

- Flyway is correctly wired
- Flyway can connect to the local PostgreSQL container
- the database schema version reached `1`
- no application tables have been added yet

## 10. Health Endpoint

Current controller:

- path: `GET /api/v1/health`
- response body:

```json
{
  "status": "UP",
  "service": "synthizer-backend"
}
```

Important runtime detail:

- because Spring Security is enabled and not yet customized, this endpoint is still protected by default
- unauthenticated access currently returns `401 Unauthorized`
- the controller itself is correct, but the security configuration does not yet permit public access

## 11. Runtime and Verification Notes

### 11.1 Maven Compile

Verified:

- `./mvnw -q -DskipTests compile` passes

### 11.2 Maven Test

Observed earlier:

- `./mvnw clean test` failed when there was no datasource configuration
- after PostgreSQL configuration was added, the deeper issue became schema validation rather than datasource discovery

### 11.3 Application Startup With Checked-In Config

With the current checked-in configuration, the application does not boot cleanly.

Reason:

- `spring.jpa.hibernate.ddl-auto=validate` requires the schema to match entities
- Flyway only creates the `pgcrypto` extension
- tables such as `notebooks` do not exist yet

Observed error:

- `Schema-validation: missing table [notebooks]`

This is expected given the current state of migrations.

### 11.4 Application Startup With Temporary Override

The application was also run with a temporary runtime-only override:

```text
--spring.jpa.hibernate.ddl-auto=none
```

Under that temporary override:

- the app started successfully
- Flyway validated migration `V1__init.sql`
- Tomcat started on port `8080`
- the JPA layer initialized
- Spring Security generated a default development password

This was only for verification. The checked-in config still uses `validate`.

### 11.5 Port Cleanup

After verification:

- a leftover Java process was found listening on port `8080`
- it was stopped
- port `8080` was confirmed free afterward

## 12. Security State

Current security posture is still default Spring Security behavior.

Observed effects:

- all endpoints are protected unless explicitly allowed
- `GET /api/v1/health` currently returns `401` without authentication
- the application prints a generated default password at startup

What is not done yet:

- no JWT auth implementation
- no public endpoint rules
- no custom `SecurityFilterChain`
- no authentication/authorization business logic

## 13. Database / Domain State

Current domain entities exist, but the database schema does not match them yet.

Entities currently present:

- `User`
- `Notebook`
- `Asset`
- `Generation`

Current mismatch:

- the entities are present in code
- the tables are not yet created in Flyway migrations
- JPA validation therefore fails at boot with checked-in settings

## 14. Git and Repository History

Repository state:

- Git initialized locally
- GitHub remote connected
- branch: `main`
- remote: `origin`

Recent commits:

- `7fecadb` `Restructure backend modules and local setup`
- `ed4216c` `Add project README`
- `2909714` `Initial project scaffold`

## 15. README State

A `README.md` was created and pushed earlier.

Project note:

- the README currently exists
- it is functional but generic
- it was explicitly called out as too template-like and likely needs a proper rewrite later

This document is intended to be the more exact project record for now.

## 16. Dependency / Vulnerability Note

An IDE warning reported two vulnerabilities on the dependency view.

Interpretation at the time:

- the likely real issue was not `spring-boot-starter-actuator` itself
- it appeared to be a transitive Jackson issue
- `GHSA-72hv-8253-57qq` was identified as affecting `jackson-core`
- the Mend ID `WS-2026-0003` looked like a duplicate advisory from another database

Status:

- this was analyzed
- no change has been applied yet in `pom.xml` specifically to override the Jackson BOM

## 17. Known Gaps

The main unfinished items are:

- no table-creating Flyway migrations yet
- app does not boot cleanly with committed settings because schema validation fails
- health endpoint exists but is not publicly accessible yet
- auth package is placeholder-only
- asset and generation modules are placeholder-only
- mappers are empty
- service interfaces have no implementations
- README needs a better final rewrite

## 18. Recommended Next Steps

Most practical order:

1. Add Flyway migrations for `users`, `notebooks`, `assets`, and `generations`
2. Keep `spring.jpa.hibernate.ddl-auto=validate`
3. Add a real `SecurityFilterChain`
4. Permit `/api/v1/health` publicly
5. Re-run the application and verify `GET /api/v1/health`
6. Start implementing auth, user, and notebook business logic
7. Decide whether to override Jackson versions or wait for the managed Spring Boot dependency line to absorb the fix

## 19. Summary

The project is not an empty starter anymore. It now has:

- a real modular backend package structure
- Dockerized PostgreSQL
- Spring datasource configuration
- Flyway baseline migration
- MapStruct + Lombok compiler setup
- a basic health controller
- GitHub-backed version control

The main blocker now is not project structure. It is schema and security completion:

- schema, because entities exist but migrations do not
- security, because the health endpoint is still behind default Spring Security
