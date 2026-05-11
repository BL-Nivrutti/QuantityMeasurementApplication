package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.Operation;
import com.quantitymeasurement.enums.Unit;
import com.quantitymeasurement.model.GenericQuantity;

import java.util.Objects;

/**
 * UC13 - Centralized Arithmetic Logic
 *
 * <p>
 * A single, centralized service that dispatches all arithmetic operations
 * through the {@link Operation} enum. Eliminates duplication across
 * separate arithmetic service methods by using a single
 * {@code compute()} entry point.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class CentralizedArithmeticService {

    /**
     * Performs the specified operation on two quantities and returns the
     * result in the unit of the first quantity.
     *
     * @param <U>       the unit type
     * @param q1        the first operand (must not be null)
     * @param q2        the second operand (must not be null)
     * @param operation the operation to perform (must not be null)
     * @return a new GenericQuantity representing the result in q1's unit
     * @throws NullPointerException if any argument is null
     */
    public <U extends Unit> GenericQuantity<U> compute(
            GenericQuantity<U> q1,
            GenericQuantity<U> q2,
            Operation operation) {

        Objects.requireNonNull(q1, "First quantity must not be null");
        Objects.requireNonNull(q2, "Second quantity must not be null");
        Objects.requireNonNull(operation, "Operation must not be null");

        double resultInBase = operation.apply(q1.toBaseUnit(), q2.toBaseUnit());
        double resultValue = resultInBase / q1.getUnit().getConversionFactor();
        return new GenericQuantity<>(resultValue, q1.getUnit());
    }

    /**
     * Performs the specified operation on two quantities and returns the
     * result in the specified target unit.
     *
     * @param <U>        the unit type
     * @param q1         the first operand (must not be null)
     * @param q2         the second operand (must not be null)
     * @param operation  the operation to perform (must not be null)
     * @param targetUnit the unit for the result (must not be null)
     * @return a new GenericQuantity representing the result in the target unit
     */
    public <U extends Unit> GenericQuantity<U> compute(
            GenericQuantity<U> q1,
            GenericQuantity<U> q2,
            Operation operation,
            U targetUnit) {

        Objects.requireNonNull(targetUnit, "Target unit must not be null");
        double resultInBase = operation.apply(q1.toBaseUnit(), q2.toBaseUnit());
        double resultValue = resultInBase / targetUnit.getConversionFactor();
        return new GenericQuantity<>(resultValue, targetUnit);
    }
}
