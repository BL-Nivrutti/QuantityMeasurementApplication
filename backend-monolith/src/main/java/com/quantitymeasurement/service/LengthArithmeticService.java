package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.Quantity;

import java.util.Objects;

/**
 * UC6 - Addition of Two Length Units
 *
 * <p>
 * Service class providing arithmetic operations on length quantities.
 * All operations normalize values to the base unit (inches) before
 * performing arithmetic, then convert the result to the desired unit.
 * </p>
 *
 * <p>
 * Demonstrates: arithmetic operations, base unit normalization,
 * precision handling, type safety, validation, method overloading,
 * and edge-case handling.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class LengthArithmeticService {

    /**
     * Adds two length quantities and returns the result in inches (default).
     *
     * @param q1 the first quantity (must not be null)
     * @param q2 the second quantity (must not be null)
     * @return a new Quantity representing the sum in inches
     * @throws NullPointerException if either quantity is null
     */
    public Quantity add(Quantity q1, Quantity q2) {
        return add(q1, q2, LengthUnit.INCH);
    }

    /**
     * Adds two length quantities and returns the result in the specified unit.
     *
     * @param q1         the first quantity (must not be null)
     * @param q2         the second quantity (must not be null)
     * @param resultUnit the unit for the result (must not be null)
     * @return a new Quantity representing the sum in the specified unit
     * @throws NullPointerException if any argument is null
     */
    public Quantity add(Quantity q1, Quantity q2, LengthUnit resultUnit) {
        Objects.requireNonNull(q1, "First quantity must not be null");
        Objects.requireNonNull(q2, "Second quantity must not be null");
        Objects.requireNonNull(resultUnit, "Result unit must not be null");

        double sumInBase = q1.toBaseUnit() + q2.toBaseUnit();
        double resultValue = sumInBase / resultUnit.getConversionFactor();
        return new Quantity(resultValue, resultUnit);
    }

    /**
     * Adds a numeric value (in a given unit) to an existing quantity.
     *
     * @param q          the base quantity (must not be null)
     * @param value      the value to add (must be non-negative)
     * @param valueUnit  the unit of the value to add (must not be null)
     * @param resultUnit the unit for the result (must not be null)
     * @return a new Quantity representing the sum
     */
    public Quantity add(Quantity q, double value, LengthUnit valueUnit, LengthUnit resultUnit) {
        Quantity addend = new Quantity(value, valueUnit);
        return add(q, addend, resultUnit);
    }
}
