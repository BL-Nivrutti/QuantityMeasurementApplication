package com.quantitymeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UC17 - Spring Boot Backend
 *
 * <p>
 * Main entry point for the Quantity Measurement Spring Boot application.
 * Bootstraps the Spring context, auto-configures all components, and
 * starts the embedded Tomcat server.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@SpringBootApplication
public class QuantityMeasurementApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantityMeasurementApplication.class, args);
    }
}
