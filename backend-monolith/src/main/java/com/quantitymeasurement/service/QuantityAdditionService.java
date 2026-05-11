package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.Quantity;

import java.util.Objects;

/**
 * UC7 - Addition with Target Unit Specification
 *
 * <p>
 * Provides overloaded addition APIs that allow callers to specify the
 * result unit explicitly. Uses private utility methods to avoid duplication.
 * Supports cross-unit addition with flexible result representation.
 * </p>
 *
 * <p>
 * Demonstrates: method overloading, private utility methods,
 * result target unit flexibility, conversion efficiency, and
 * cross-category validation.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class QuantityAdditionService {

    /**
     * Adds two quantities and returns the result in the default unit (INCH).
     *
     * @param q1 first quantity (must not be null)
     * @param q2 second quantity (must not be null)
     * @return sum in inches
     */
    public Quantity add(Quantity q1, Quantity q2) {
        return addInternal(q1, q2, LengthUnit.INCH);
    }

    /**
     * Adds two quantities and returns the result in the specified target unit.
     *
     * @param q1         first quantity (must not be null)
     * @param q2         second quantity (must not be null)
     * @param targetUnit the desired result unit (must not be null)
     * @return sum in the target unit
     */
    public Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        return addInternal(q1, q2, targetUnit);
    }

    /**
     * Adds a list of quantities and returns the result in the specified unit.
     *
     * @param targetUnit the desired result unit (must not be null)
     * @param quantities varargs of quantities to add (must not be null or empty)
     * @return sum of all quantities in the target unit
     * @throws IllegalArgumentException if no quantities are provided
     */
    public Quantity addAll(LengthUnit targetUnit, Quantity... quantities) {
        Objects.requireNonNull(targetUnit, "Target unit must not be null");
        if (quantities == null || quantities.length == 0) {
            throw new IllegalArgumentException("At least one quantity must be provided");
        }
        double totalBase = 0.0;
        for (Quantity q : quantities) {
            Objects.requireNonNull(q, "Quantity in list must not be null");
            totalBase += q.toBaseUnit();
        }
        double resultValue = totalBase / targetUnit.getConversionFactor();
        return new Quantity(resultValue, targetUnit);
    }

    // ─── Private Utility ──────────────────────────────────────────────────────

    /**
     * Internal addition logic: normalizes both quantities to base unit,
     * sums them, then converts to the target unit.
     *
     * @param q1         first quantity
     * @param q2         second quantity
     * @param targetUnit result unit
     * @return sum in target unit
     */
    private Quantity addInternal(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        Objects.requireNonNull(q1, "First quantity must not be null");
        Objects.requireNonNull(q2, "Second quantity must not be null");
        Objects.requireNonNull(targetUnit, "Target unit must not be null");

        double sumInBase = q1.toBaseUnit() + q2.toBaseUnit();
        double resultValue = sumInBase / targetUnit.getConversionFactor();
        return new Quantity(resultValue, targetUnit);
    }
}
