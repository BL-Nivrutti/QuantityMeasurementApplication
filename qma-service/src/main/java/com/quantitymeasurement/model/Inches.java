package com.quantitymeasurement.model;

import java.util.Objects;

/**
 * UC2 - Feet and Inches Measurement Equality
 *
 * <p>
 * Represents a measurement in inches. Implements proper object equality
 * using floating-point comparison with epsilon tolerance. Encapsulates
 * the value and provides clean OOP structure.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class Inches {

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-9;

    /** The measurement value in inches */
    private final double value;

    /**
     * Constructs an Inches measurement with the given value.
     *
     * @param value the measurement value in inches (must be non-negative)
     * @throws IllegalArgumentException if value is negative
     */
    public Inches(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Inches value cannot be negative: " + value);
        }
        this.value = value;
    }

    /**
     * Returns the measurement value in inches.
     *
     * @return the value in inches
     */
    public double getValue() {
        return value;
    }

    /**
     * Converts this inches measurement to feet.
     *
     * @return equivalent value in feet (1 inch = 1/12 feet)
     */
    public double toFeet() {
        return value / 12.0;
    }

    /**
     * Checks equality between two Inches objects using floating-point tolerance.
     *
     * @param obj the object to compare with
     * @return {@code true} if both measurements are equal within tolerance
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Inches))
            return false;
        Inches other = (Inches) obj;
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
     * Returns a string representation of this Inches measurement.
     *
     * @return string in format "X.X inches"
     */
    @Override
    public String toString() {
        return value + " inches";
    }
}
