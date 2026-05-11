package com.quantitymeasurement.model;

import java.util.Objects;

/**
 * UC2 - Feet and Inches Measurement Equality
 *
 * <p>
 * Represents a combined measurement in feet and inches. Supports equality
 * comparison by normalizing both components to a single base unit (inches).
 * Demonstrates encapsulation and clean OOP design.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class FeetAndInches {

    /** Conversion factor: 1 foot = 12 inches */
    private static final double INCHES_PER_FOOT = 12.0;

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-9;

    /** The feet component */
    private final double feet;

    /** The inches component */
    private final double inches;

    /**
     * Constructs a FeetAndInches measurement.
     *
     * @param feet   the feet component (must be non-negative)
     * @param inches the inches component (must be non-negative)
     * @throws IllegalArgumentException if either value is negative
     */
    public FeetAndInches(double feet, double inches) {
        if (feet < 0) {
            throw new IllegalArgumentException("Feet value cannot be negative: " + feet);
        }
        if (inches < 0) {
            throw new IllegalArgumentException("Inches value cannot be negative: " + inches);
        }
        this.feet = feet;
        this.inches = inches;
    }

    /**
     * Returns the feet component.
     *
     * @return feet value
     */
    public double getFeet() {
        return feet;
    }

    /**
     * Returns the inches component.
     *
     * @return inches value
     */
    public double getInches() {
        return inches;
    }

    /**
     * Converts the total measurement to inches (base unit).
     *
     * @return total value in inches
     */
    public double toTotalInches() {
        return (feet * INCHES_PER_FOOT) + inches;
    }

    /**
     * Converts the total measurement to feet.
     *
     * @return total value in feet
     */
    public double toTotalFeet() {
        return toTotalInches() / INCHES_PER_FOOT;
    }

    /**
     * Checks equality by comparing total inches of both measurements.
     *
     * @param obj the object to compare with
     * @return {@code true} if both measurements represent the same total length
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FeetAndInches))
            return false;
        FeetAndInches other = (FeetAndInches) obj;
        return Math.abs(this.toTotalInches() - other.toTotalInches()) < EPSILON;
    }

    /**
     * Returns a hash code consistent with the equals contract.
     *
     * @return hash code based on total inches
     */
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toTotalInches() / EPSILON) * EPSILON);
    }

    /**
     * Returns a string representation of this measurement.
     *
     * @return string in format "X.X feet Y.Y inches"
     */
    @Override
    public String toString() {
        return feet + " feet " + inches + " inches";
    }
}
