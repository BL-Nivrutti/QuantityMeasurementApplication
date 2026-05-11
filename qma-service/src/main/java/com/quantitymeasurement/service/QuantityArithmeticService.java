package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.Unit;
import com.quantitymeasurement.exception.QuantityArithmeticException;
import com.quantitymeasurement.model.GenericQuantity;

import java.util.Objects;

/**
 * UC12 - Subtraction and Division Operations
 *
 * <p>
 * Provides full arithmetic operations (add, subtract, divide) on
 * {@link GenericQuantity} objects. Handles division-by-zero, validates
 * inputs, and ensures non-commutative operations (subtract, divide)
 * are handled correctly.
 * </p>
 *
 * <p>
 * Demonstrates: arithmetic operations, division-by-zero handling,
 * validation consistency, precision handling, non-commutative operation
 * handling.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class QuantityArithmeticService {

    /**
     * Adds two quantities of the same unit category.
     *
     * @param <U> the unit type
     * @param q1  first quantity (must not be null)
     * @param q2  second quantity (must not be null)
     * @return sum in the unit of q1
     */
    public <U extends Unit> GenericQuantity<U> add(GenericQuantity<U> q1, GenericQuantity<U> q2) {
        validateNotNull(q1, q2);
        double sumInBase = q1.toBaseUnit() + q2.toBaseUnit();
        return new GenericQuantity<>(sumInBase / q1.getUnit().getConversionFactor(), q1.getUnit());
    }

    /**
     * Subtracts q2 from q1. Result is in the unit of q1.
     *
     * <p>
     * Note: subtraction is non-commutative.
     * {@code subtract(q1, q2) != subtract(q2, q1)}
     * unless both are equal.
     * </p>
     *
     * @param <U> the unit type
     * @param q1  the minuend (must not be null)
     * @param q2  the subtrahend (must not be null)
     * @return difference in the unit of q1
     * @throws QuantityArithmeticException if result would be negative
     */
    public <U extends Unit> GenericQuantity<U> subtract(GenericQuantity<U> q1, GenericQuantity<U> q2) {
        validateNotNull(q1, q2);
        double diffInBase = q1.toBaseUnit() - q2.toBaseUnit();
        if (diffInBase < -1e-9) {
            throw new QuantityArithmeticException(
                    "Subtraction result is negative: " + q1 + " - " + q2);
        }
        double resultValue = Math.max(0.0, diffInBase) / q1.getUnit().getConversionFactor();
        return new GenericQuantity<>(resultValue, q1.getUnit());
    }

    /**
     * Divides q1 by q2 and returns the scalar ratio.
     *
     * <p>
     * Returns a dimensionless ratio (double), not a quantity.
     * </p>
     *
     * @param <U> the unit type
     * @param q1  the dividend (must not be null)
     * @param q2  the divisor (must not be null, must not be zero)
     * @return the ratio q1 / q2
     * @throws QuantityArithmeticException if q2 is zero
     */
    public <U extends Unit> double divide(GenericQuantity<U> q1, GenericQuantity<U> q2) {
        validateNotNull(q1, q2);
        double divisorBase = q2.toBaseUnit();
        if (Math.abs(divisorBase) < 1e-12) {
            throw new QuantityArithmeticException("Division by zero is not allowed");
        }
        return q1.toBaseUnit() / divisorBase;
    }

    /**
     * Divides q1 by a scalar value and returns a new quantity in the same unit.
     *
     * @param <U>    the unit type
     * @param q1     the dividend (must not be null)
     * @param scalar the divisor scalar (must not be zero)
     * @return a new quantity representing q1 / scalar
     * @throws QuantityArithmeticException if scalar is zero
     */
    public <U extends Unit> GenericQuantity<U> divideByScalar(GenericQuantity<U> q1, double scalar) {
        Objects.requireNonNull(q1, "Quantity must not be null");
        if (Math.abs(scalar) < 1e-12) {
            throw new QuantityArithmeticException("Division by zero scalar is not allowed");
        }
        return new GenericQuantity<>(q1.getValue() / scalar, q1.getUnit());
    }

    // ─── Private Validation ───────────────────────────────────────────────────

    private <U extends Unit> void validateNotNull(GenericQuantity<U> q1, GenericQuantity<U> q2) {
        Objects.requireNonNull(q1, "First quantity must not be null");
        Objects.requireNonNull(q2, "Second quantity must not be null");
    }
}
