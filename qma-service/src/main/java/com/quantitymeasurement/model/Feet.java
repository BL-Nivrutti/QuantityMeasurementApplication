package com.quantitymeasurement.model;

import java.util.Objects;

/**
 * UC1 - Feet Measurement Equality
 *
 * <p>Represents a measurement in feet. Implements proper object equality
 * using floating-point comparison with a defined tolerance (epsilon).
 * Handles null checking, type checking, and follows OOP design principles.</p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class Feet {

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-9;

    /** The measurement value in feet */
    private final double value;

    /**
     * Constructs a Feet measurement with the given value.
     *
     * @param value the measurement value in feet (must be non-negative)
     * @throws IllegalArgumentException if value is negative
     */
    public Feet(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Feet value cannot be negative: " + value);
        }
        this.value = value;
    }

    /**
     * Returns the measurement value in feet.
     *
     * @return the value in feet
     */
    public double getValue() {
        return value;
    }

    /**
     * Checks equality between two Feet objects using floating-point tolerance.
     *
     * <p>Two Feet measurements are considered equal if their values differ
     * by less than {@code EPSILON}.</p>
     *
     * @param obj the object to compare with
     * @return {@code true} if both measurements are equal within tolerance
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Feet)) return false;
        Feet other = (Feet) obj;
        return Math.abs(this.value - other.value) < EPSILON;
    }

    /**
     * Returns a hash code consistent with the equals contract.
     *
     * @return hash code based on the rounded value
     */
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(value / EPSILON) * EPSILON);
    }

    /**
     * Returns a string representation of this Feet measurement.
     *
     * @return string in format "X.X feet"
     */
    @Override
    public String toString() {
        return value + " feet";
    }
}
