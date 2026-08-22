# AI-Powered Internship & Career Development Platform — Backend

Java Spring Boot 3.x REST API (Member 4 — Backend Engineer) for the 4-member team project.
Frontend (React + Vite) consumes these APIs over JSON.

## Tech Stack
- Java 17, Spring Boot 3.3.4
- PostgreSQL (via pgAdmin 4)
- Spring Security + JWT (stateless), RBAC: STUDENT / COMPANY / ADMIN
- Spring Data JPA / Hibernate
- Maven (wrapper pinned to Maven 4.1.0)
- Lombok
- springdoc-openapi (Swagger UI) for API docs

## Prerequisites
- JDK 17
- PostgreSQL 14+ running locally (or update `application.properties`)
- Maven wrapper included — no local Maven install required

## Setup

1. Create the database:
   ```sql
   CREATE DATABASE internship_db;
   ```

2. Update `src/main/resources/application.properties` with your local PostgreSQL
   username/password if different from the defaults.

3. Run the app:
   ```bash
   ./mvnw spring-boot:run        # macOS/Linux
   mvnw.cmd spring-boot:run      # Windows
   ```

4. API base URL: `http://localhost:8080`
5. Swagger UI (API docs for the frontend team): `http://localhost:8080/swagger-ui.html`

## Project Structure

```
src/main/java/com/sitoula/internship/
├── config/          SecurityConfig, CorsConfig, OpenApiConfig
├── security/         JWT provider/filter/entry point, UserDetails
├── entity/           JPA entities (User, StudentProfile, CompanyProfile,
│                      Skill, Education, Project, Certification,
│                      Internship, Application, + enums)
├── repository/        Spring Data JPA repositories
├── dto/
│   ├── request/       Incoming request bodies
│   └── response/       Outgoing response bodies (+ ApiResponse<T> wrapper)
├── service/           Business logic (Auth, Student, Company, Internship,
│                      Application, Admin, FileStorage, MatchScore)
├── controller/        @RestController endpoints
├── exception/         Custom exceptions + GlobalExceptionHandler
└── util/              Shared constants
```

## API Modules
- `/api/auth` — register, login (public)
- `/api/student` — profile, skills, education, projects, certifications, resume (STUDENT only)
- `/api/internships` — list/search/filter (any authenticated role), create/update/delete (COMPANY only)
- `/api/applications` — apply/track (STUDENT), view applicants/update status (COMPANY, ADMIN)
- `/api/admin` — dashboard stats, user management, company verification (ADMIN only)

## Notes for Frontend Team
- Every endpoint returns `ApiResponse<T>`: `{ success, message, data }`.
- Auth: send `Authorization: Bearer <token>` header after login/register.
- CORS is pre-configured for `http://localhost:5173` (Vite dev server) — update
  `app.cors.allowed-origins` in `application.properties` if your dev port differs.
