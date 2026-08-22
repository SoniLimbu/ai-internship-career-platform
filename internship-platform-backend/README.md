# AI-Powered Internship & Career Development Platform — Backend

A production-ready Java Spring Boot REST API powering the **AI-Powered Internship & Career Development Platform** — a web application that helps university students manage their career profiles, discover internships, and track applications, while giving companies a way to post openings and manage candidates.

Built as the backend service (Member 4 — Backend Engineer) for a 4-member team project. The frontend (React + Vite, built by Members 1–3) consumes these APIs over JSON.

**Author:** Ushudha Sanwa Limbu — Backend Engineer, Java Backend Development Intern @ Sitoula Tech Solutions

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Overview](#api-overview)
- [Authentication](#authentication)
- [Role-Based Access](#role-based-access)
- [Notes for the Frontend Team](#notes-for-the-frontend-team)
- [Status](#status)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.3.4 |
| Security | Spring Security + JWT (stateless), RBAC |
| Persistence | Spring Data JPA / Hibernate |
| Database | PostgreSQL 14+ |
| Build Tool | Maven |
| API Docs | springdoc-openapi (Swagger UI) |
| Utilities | Lombok |

---

## Prerequisites

- JDK 17
- PostgreSQL 14+ running locally (or a remote instance — update `application.properties` accordingly)
- Maven wrapper is included in the repo, so a local Maven install is not required

---

## Getting Started

### 1. Create the database

In pgAdmin or `psql`:

```sql
CREATE DATABASE internship_platform;
```

### 2. Configure your local environment

Open `src/main/resources/application.properties` and update the datasource credentials to match your local PostgreSQL setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/internship_platform
spring.datasource.username=postgres
spring.datasource.password=<your_local_password>
```

### 3. Run the application

```bash
./mvnw spring-boot:run        # macOS/Linux
mvnw.cmd spring-boot:run      # Windows
```

Or run `InternshipPlatformApplication.java` directly from your IDE.

On first run, Hibernate (`spring.jpa.hibernate.ddl-auto=update`) will automatically create all required tables in `internship_platform`.

### 4. Verify it's running

| What | URL |
|---|---|
| API base URL | `http://localhost:8080` |
| Swagger UI (interactive API docs) | `http://localhost:8080/swagger-ui.html` |
| OpenAPI JSON spec | `http://localhost:8080/api-docs` |

---

## Project Structure

```
src/main/java/com/sitoula/internship/
├── config/           SecurityConfig, CorsConfig, OpenApiConfig
├── security/         JWT provider/filter/entry point, CustomUserDetails
├── entity/            JPA entities — User, StudentProfile, CompanyProfile,
│                       Skill, Education, Project, Certification,
│                       Internship, Application, Role, ApplicationStatus
├── repository/         Spring Data JPA repositories
├── dto/
│   ├── request/         Incoming request bodies
│   └── response/         Outgoing response bodies + ApiResponse<T> wrapper
├── service/            Business logic — Auth, Student, Company, Internship,
│                       Application, Admin, FileStorage, MatchScore
├── controller/         @RestController endpoints
├── exception/          Custom exceptions + GlobalExceptionHandler
└── util/               Shared constants
```

---

## API Overview

| Module | Base path | Access |
|---|---|---|
| Authentication | `/api/auth` | Public |
| Student | `/api/student` | STUDENT only |
| Internships | `/api/internships` | GET: any authenticated role · POST/PUT/DELETE: COMPANY only |
| Applications | `/api/applications` | Apply/track: STUDENT · View applicants/update status: COMPANY, ADMIN |
| Admin | `/api/admin` | ADMIN only |

### Key endpoints

<details>
<summary><strong>Authentication</strong></summary>

- `POST /api/auth/register` — Register a new user (auto-creates a Student or Company profile based on role)
- `POST /api/auth/login` — Authenticate and receive a JWT

</details>

<details>
<summary><strong>Student</strong></summary>

- `GET /api/student/profile` — Full profile (skills, education, projects, certifications, resume)
- `PUT /api/student/profile` — Update profile details
- `POST /api/student/skills` · `DELETE /api/student/skills/{id}`
- `POST /api/student/education`
- `POST /api/student/projects`
- `POST /api/student/certifications`
- `POST /api/student/upload-resume` — Multipart PDF upload

</details>

<details>
<summary><strong>Internships</strong></summary>

- `GET /api/internships` — List with optional `location`, `skill`, `type` filters
- `GET /api/internships/{id}`
- `POST /api/internships` · `PUT /api/internships/{id}` · `DELETE /api/internships/{id}`

</details>

<details>
<summary><strong>Applications</strong></summary>

- `POST /api/applications/apply` — Apply to an internship (auto-computes `aiMatchScore` from skill overlap)
- `GET /api/applications/my-applications` — A student's application history
- `GET /api/applications/internship/{internshipId}` — Applicants for a company's posting
- `PATCH /api/applications/{id}/status` — Update application status

</details>

<details>
<summary><strong>Admin</strong></summary>

- `GET /api/admin/dashboard` — Aggregated platform stats
- `GET /api/admin/users`
- `PATCH /api/admin/users/{id}/verify` — Verify a company account

</details>

For the full request/response schema of every endpoint, see the [Swagger UI](http://localhost:8080/swagger-ui.html) once the app is running.

---

## Authentication

This API uses stateless JWT authentication.

1. Register or log in via `/api/auth/register` or `/api/auth/login`.
2. The response includes a `token`.
3. Include it on every subsequent request:
   ```
   Authorization: Bearer <token>
   ```

---

## Role-Based Access

| Role | Can do |
|---|---|
| `STUDENT` | Manage own profile/skills/education/projects/certifications, apply to internships, track application status |
| `COMPANY` | Post/manage internships, view applicants, update application status |
| `ADMIN` | View dashboard stats, manage users, verify companies |



## Notes for the Frontend Team

- Every endpoint returns a consistent envelope: `{ success, message, data }` (`ApiResponse<T>`).
- CORS is pre-configured for the Vite dev server at `http://localhost:5173`. If your dev port differs, update `app.cors.allowed-origins` in `application.properties`.
- Validation errors return `400` with a field-level error map in `data`.
- Unauthenticated requests to protected routes return `401`; authenticated-but-wrong-role requests return `403`.


## Status

All modules implemented and manually verified via Swagger UI: authentication, student profile management, internship posting, application workflow, and role-based access control.

---

<sub>Maintained by **Ushudha Sanwa Limbu** — Backend Engineer for the AI-Powered Internship & Career Development Platform.</sub>
