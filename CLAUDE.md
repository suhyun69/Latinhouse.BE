# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build
./gradlew build

# Run
./gradlew bootRun

# Test (all)
./gradlew test

# Test (single)
./gradlew test --tests "com.latinhouse.backend.SomeTest"

# Clean build
./gradlew clean build
```

## Secret Configuration

`application-secret.yml` is gitignored. Copy the template and fill in values before running:

```bash
cp src/main/resources/application-secret.yml.default src/main/resources/application-secret.yml
```

Required values:
- `custom.jwt.secretKey` — must be longer than 32 characters
- Kakao OAuth `client-id` if using social login

## Architecture

This project follows **Hexagonal Architecture (Ports & Adapters)** per domain.

Each domain (e.g. `user`, `auth`) is structured as:

```
{domain}/
  adapter/
    in/web/          # Controller (WebRequest/WebResponse DTOs)
    out/persistence/ # JPA adapter (JpaEntity, Mapper, Repository)
  application/service/ # Use case implementations
  domain/            # Pure domain model
  port/
    in/              # Use case interfaces + AppRequest/AppResponse DTOs
    out/             # Persistence port interfaces
```

**Data flow:** Controller → (WebRequest → AppRequest) → UseCase → Service → Port → PersistenceAdapter → JPA

**DTO conversion layers:**
- `WebRequest/WebResponse` — HTTP layer (adapter/in/web)
- `AppRequest/AppResponse` — application layer (port/in)
- `JpaEntity` — persistence layer (adapter/out/persistence/entity)
- `Mapper` classes handle conversion between layers

## Security

Two `SecurityFilterChain` beans exist:

- `SecurityConfig` — matches `/**`, allows Swagger (`/swagger-ui/**`, `/v3/api-docs/**`) and H2 console, denies everything else
- `ApiSecurityConfig` — matches `/api/**`, stateless JWT auth, permits `/api/*/auth/**` publicly

JWT is stored as a cookie (`accessToken`). Refresh token (7-day) is persisted via `TokenPersistenceAdapter`. Access token expires in 10 hours.

## Exception Handling

Custom exceptions extend `CustomException` with an `ErrorCode` enum (message + HTTP status). All exceptions are caught in `GlobalExceptionHandler`.

## Active Profile

Default profile is `dev` with H2 in-memory DB. Profile `secret` is always included for secret values.
