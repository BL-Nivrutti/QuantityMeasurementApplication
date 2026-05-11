package com.quantitymeasurement.controller;

import com.quantitymeasurement.exception.InvalidUnitException;
import com.quantitymeasurement.exception.QuantityArithmeticException;
import com.quantitymeasurement.exception.UnsupportedCategoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * UC17 - Spring Boot Backend: Global Exception Handler
 *
 * <p>
 * Centralized exception handling for all REST controllers.
 * Returns structured error responses with HTTP status codes.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidUnitException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidUnit(InvalidUnitException ex) {
        log.warn("Invalid unit: {}", ex.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UnsupportedCategoryException.class)
    public ResponseEntity<Map<String, Object>> handleUnsupportedCategory(UnsupportedCategoryException ex) {
        log.warn("Unsupported category: {}", ex.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(QuantityArithmeticException.class)
    public ResponseEntity<Map<String, Object>> handleArithmetic(QuantityArithmeticException ex) {
        log.warn("Arithmetic error: {}", ex.getMessage());
        return buildError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> handleNullPointer(NullPointerException ex) {
        log.error("Null pointer: {}", ex.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, "Required field is missing");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    private ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
