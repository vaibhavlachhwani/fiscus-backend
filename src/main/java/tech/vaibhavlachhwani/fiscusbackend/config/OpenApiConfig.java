package tech.vaibhavlachhwani.fiscusbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Fiscus Financial Management API",
                version = "1.0",
                description = "Comprehensive backend for financial transaction tracking, user role management, and business transaction summary. Built with Spring Boot 3 and secured with JWT.",
                contact = @Contact(
                        name = "Vaibhav Lachhwani",
                        email = "vaibhav.lachhwani111@gmail.com"
                )
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
