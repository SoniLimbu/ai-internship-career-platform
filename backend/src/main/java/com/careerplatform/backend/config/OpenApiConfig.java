package com.careerplatform.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Serves interactive, testable API documentation for the three frontend
 * teammates at /swagger-ui.html (raw spec at /v3/api-docs).
 *
 * Click "Authorize" in the UI, paste a JWT from POST /api/auth/login,
 * and every protected endpoint becomes callable directly from the browser —
 * no Postman required, though a Postman collection is also provided
 * (see postman/career-platform.postman_collection.json).
 */
@Configuration
public class OpenApiConfig {

    private static final String BEARER_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI careerPlatformOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI-Powered Internship & Career Development Platform — API")
                        .description("""
                                Java/Spring Boot backend for the internship platform (Member 4's scope).
                                Consumed by the React + Vite frontend built by Members 1–3.

                                Auth model: register/login return a JWT. Send it as
                                `Authorization: Bearer <token>` on every protected request.
                                Public endpoints (no token needed): POST /api/auth/register,
                                POST /api/auth/login, GET /api/internships/**, GET /api/companies/**.
                                """)
                        .version("v0.1.0")
                        .contact(new Contact().name("Member 4 — Backend & Database")))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_SCHEME))
                .components(new Components()
                        .addSecuritySchemes(BEARER_SCHEME, new SecurityScheme()
                                .name(BEARER_SCHEME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}