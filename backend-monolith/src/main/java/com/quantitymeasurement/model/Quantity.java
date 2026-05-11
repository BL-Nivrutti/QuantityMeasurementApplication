package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;

import java.util.Objects;

/**
 * UC3 - Generic Quantity Class
 *
 * <p>
 * A generic, reusable quantity class that applies the DRY principle by
 * replacing separate Feet and Inches classes with a single abstraction.
 * Uses {@link LengthUnit} enum to represent the unit type and normalizes
 * all values to a base unit (inches) for comparison.
 * </p>
 *
 * <p>
 * Demonstrates: DRY, polymorphism, enum usage, abstraction,
 * equals override, SRP, and refactoring best practices.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class Quantity {

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-9;

    /** The numeric value of this measurement */
    private final double value;

    /** The unit of this measurement */
    private final LengthUnit unit;

    /**
     * Constructs a Quantity with the given value and unit.
     *
     * @param value the numeric measurement value (must be non-negative)
     * @param unit  the unit of measurement (must not be null)
     * @throws IllegalArgumentException if value is negative
     * @throws NullPointerException     if unit is null
     */
    public Quantity(double value, LengthUnit unit) {
        if (value < 0) {
            throw new IllegalArgumentException("Quantity value cannot be negative: " + value);
        }
        this.value = Objects.requireNonNull(unit, "Unit must not be null") != null ? value : value;
        this.unit = Objects.requireNonNull(unit, "Unit must not be null");
    }

    /**
     * Returns the numeric value of this measurement.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the unit of this measurement.
     *
     * @return the unit
     */
    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Converts this quantity to the base unit (inches).
     *
     * @return value in inches
     */
    public double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    /**
     * Checks equality by comparing base-unit values within epsilon tolerance.
     *
     * <p>
     * Two quantities are equal if they represent the same physical length,
     * regardless of the unit used to express them.
     * </p>
     *
     * @param obj the object to compare with
     * @return {@code true} if both quantities represent the same length
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Quantity))
            return false;
        Quantity other = (Quantity) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    /**
     * Returns a hash code consistent with the equals contract.
     *
     * @return hash code based on base-unit value
     */
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit() / EPSILON) * EPSILON);
    }

    /**
     * Returns a string representation of this quantity.
     *
     * @return string in format "X.X UNIT"
     */
    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }
}
