package com.quantitymeasurement.enums;

import com.quantitymeasurement.exception.QuantityArithmeticException;

import java.util.function.DoubleBinaryOperator;

/**
 * UC13 - Centralized Arithmetic Logic
 *
 * <p>
 * Enum-based operation dispatch using functional interfaces and lambda
 * expressions. Each operation encapsulates its logic as a
 * {@link DoubleBinaryOperator}, enabling DRY enforcement and a single
 * source of truth for all arithmetic operations.
 * </p>
 *
 * <p>
 * Demonstrates: functional interfaces, lambda expressions,
 * enum-based operation dispatch, DRY enforcement, consistent
 * validation handling.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public enum Operation {

    /** Addition: a + b */
    ADD((a, b) -> a + b),

    /** Subtraction: a - b (throws if result is negative) */
    SUBTRACT((a, b) -> {
        double result = a - b;
        if (result < -1e-9) {
            throw new QuantityArithmeticException(
                    "Subtraction result is negative: " + a + " - " + b);
        }
        return Math.max(0.0, result);
    }),

    /** Multiplication: a * b */
    MULTIPLY((a, b) -> a * b),

    /** Division: a / b (throws on division by zero) */
    DIVIDE((a, b) -> {
        if (Math.abs(b) < 1e-12) {
            throw new QuantityArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    });

    /** The binary operator implementing this operation */
    private final DoubleBinaryOperator operator;

    Operation(DoubleBinaryOperator operator) {
        this.operator = operator;
    }

    /**
     * Applies this operation to two base-unit values.
     *
     * @param a the first operand (in base units)
     * @param b the second operand (in base units)
     * @return the result of the operation
     * @throws QuantityArithmeticException for invalid operations (e.g., divide by
     *                                     zero)
     */
    public double apply(double a, double b) {
        return operator.applyAsDouble(a, b);
    }
}
