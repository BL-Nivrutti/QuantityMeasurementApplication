package com.quantitymeasurement.exception;

/**
 * UC15 - N-Tier Architecture: Exception Layer
 *
 * <p>
 * Thrown when an invalid or unrecognized unit is provided.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class InvalidUnitException extends RuntimeException {

    public InvalidUnitException(String message) {
        super(message);
    }

    public InvalidUnitException(String message, Throwable cause) {
        super(message, cause);
    }
}
