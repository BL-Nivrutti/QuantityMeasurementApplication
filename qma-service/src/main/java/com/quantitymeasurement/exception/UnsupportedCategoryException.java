package com.quantitymeasurement.exception;

/**
 * UC15 - N-Tier Architecture: Exception Layer
 *
 * <p>
 * Thrown when an unsupported measurement category is requested.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class UnsupportedCategoryException extends RuntimeException {

    public UnsupportedCategoryException(String message) {
        super(message);
    }
}
