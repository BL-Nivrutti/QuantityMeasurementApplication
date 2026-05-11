package com.quantitymeasurement.config;

import com.quantitymeasurement.service.ConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UC17 - Spring Boot Backend: Application Configuration
 *
 * <p>
 * Spring configuration class that registers domain service beans
 * not managed by component scanning (plain Java classes).
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Configuration
public class AppConfig {

    /**
     * Registers the domain ConversionService as a Spring bean.
     *
     * @return a new ConversionService instance
     */
    @Bean
    public ConversionService conversionService() {
        return new ConversionService();
    }
}
