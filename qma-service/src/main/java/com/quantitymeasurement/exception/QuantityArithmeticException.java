package com.quantitymeasurement.exception;

/**
 * UC12 - Quantity Arithmetic Exception
 *
 * <p>
 * Thrown when an invalid arithmetic operation is attempted on quantities,
 * such as division by zero or a subtraction that would yield a negative result.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class QuantityArithmeticException extends RuntimeException {

    /**
     * Constructs a QuantityArithmeticException with the given message.
     *
     * @param message the detail message
     */
    public QuantityArithmeticException(String message) {
        super(message);
    }

    /**
     * Constructs a QuantityArithmeticException with the given message and cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public QuantityArithmeticException(String message, Throwable cause) {
        super(message, cause);
    }
}
