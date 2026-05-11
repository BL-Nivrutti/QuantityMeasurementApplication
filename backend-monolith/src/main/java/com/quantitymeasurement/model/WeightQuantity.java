package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.WeightUnit;

import java.util.Objects;

/**
 * UC9 - Weight Measurement
 *
 * <p>
 * Immutable value object representing a weight measurement. Supports
 * equality comparison, conversion between weight units, and arithmetic
 * operations via base unit normalization (grams).
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class WeightQuantity {

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-6;

    /** The numeric value of this weight measurement */
    private final double value;

    /** The unit of this weight measurement */
    private final WeightUnit unit;

    /**
     * Constructs a WeightQuantity with the given value and unit.
     *
     * @param value the numeric weight value (must be non-negative)
     * @param unit  the weight unit (must not be null)
     * @throws IllegalArgumentException if value is negative
     * @throws NullPointerException     if unit is null
     */
    public WeightQuantity(double value, WeightUnit unit) {
        if (value < 0) {
            throw new IllegalArgumentException("Weight value cannot be negative: " + value);
        }
        this.unit = Objects.requireNonNull(unit, "Weight unit must not be null");
        this.value = value;
    }

    /**
     * Returns the numeric value of this weight measurement.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the unit of this weight measurement.
     *
     * @return the unit
     */
    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Converts this weight to the base unit (grams).
     *
     * @return value in grams
     */
    public double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    /**
     * Converts this weight to the specified target unit.
     *
     * @param targetUnit the unit to convert to (must not be null)
     * @return a new WeightQuantity in the target unit
     */
    public WeightQuantity convertTo(WeightUnit targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit must not be null");
        double baseValue = toBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new WeightQuantity(convertedValue, targetUnit);
    }

    /**
     * Adds another weight quantity to this one and returns the result in this unit.
     *
     * @param other the weight to add (must not be null)
     * @return a new WeightQuantity representing the sum in this unit
     */
    public WeightQuantity add(WeightQuantity other) {
        Objects.requireNonNull(other, "Other weight must not be null");
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();
        double resultValue = sumInBase / this.unit.getConversionFactor();
        return new WeightQuantity(resultValue, this.unit);
    }

    /**
     * Checks equality by comparing base-unit values within epsilon tolerance.
     *
     * @param obj the object to compare with
     * @return {@code true} if both weights represent the same physical weight
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof WeightQuantity))
            return false;
        WeightQuantity other = (WeightQuantity) obj;
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
     * Returns a string representation of this weight measurement.
     *
     * @return string in format "X.X UNIT"
     */
    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }
}
