package com.quantitymeasurement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UC17 - Spring Boot Backend: OpenAPI / Swagger Configuration
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI quantityMeasurementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quantity Measurement API")
                        .description(
                                "Enterprise-grade REST API for unit conversions across Length, Weight, Volume, and Temperature")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Nivrutti")
                                .url("https://github.com/BL-Nivrutti/QuantityMeasurementApplication"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
