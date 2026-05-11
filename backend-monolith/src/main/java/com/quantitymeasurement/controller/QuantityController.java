package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.ConversionRequestDto;
import com.quantitymeasurement.dto.ConversionResponseDto;
import com.quantitymeasurement.entity.ConversionHistory;
import com.quantitymeasurement.service.QuantityConversionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UC17 - Spring Boot Backend: REST Controller
 *
 * <p>
 * REST controller exposing quantity conversion endpoints.
 * Follows RESTful conventions and uses Spring MVC annotations.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/quantity")
@Tag(name = "Quantity Conversion", description = "APIs for unit conversion across Length, Weight, Volume, and Temperature")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class QuantityController {

    private static final Logger log = LoggerFactory.getLogger(QuantityController.class);

    private final QuantityConversionService quantityConversionService;

    public QuantityController(QuantityConversionService quantityConversionService) {
        this.quantityConversionService = quantityConversionService;
    }

    /**
     * Converts a quantity from one unit to another.
     *
     * @param request the conversion request body
     * @return the conversion result
     */
    @PostMapping("/convert")
    @Operation(summary = "Convert a quantity", description = "Converts a value from one unit to another within the same category")
    public ResponseEntity<ConversionResponseDto> convert(@RequestBody ConversionRequestDto request) {
        log.info("POST /api/quantity/convert - {}", request);
        ConversionResponseDto response = quantityConversionService.convert(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Returns all conversion history.
     *
     * @return list of all conversion history records
     */
    @GetMapping("/history")
    @Operation(summary = "Get conversion history", description = "Returns all past conversion records")
    public ResponseEntity<List<ConversionHistory>> getHistory() {
        return ResponseEntity.ok(quantityConversionService.getHistory());
    }

    /**
     * Returns conversion history filtered by category.
     *
     * @param category the measurement category (LENGTH, WEIGHT, VOLUME,
     *                 TEMPERATURE)
     * @return filtered history records
     */
    @GetMapping("/history/{category}")
    @Operation(summary = "Get history by category")
    public ResponseEntity<List<ConversionHistory>> getHistoryByCategory(
            @Parameter(description = "Measurement category: LENGTH, WEIGHT, VOLUME, TEMPERATURE") @PathVariable String category) {
        return ResponseEntity.ok(quantityConversionService.getHistoryByCategory(category));
    }

    /**
     * Returns the most recent N conversion records.
     *
     * @param limit the number of records to return (default 10)
     * @return recent history records
     */
    @GetMapping("/history/recent")
    @Operation(summary = "Get recent conversion history")
    public ResponseEntity<List<ConversionHistory>> getRecentHistory(
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(quantityConversionService.getRecentHistory(limit));
    }

    /**
     * Health check endpoint.
     *
     * @return status message
     */
    @GetMapping("/health")
    @Operation(summary = "Health check")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Quantity Measurement Service is running");
    }
}
