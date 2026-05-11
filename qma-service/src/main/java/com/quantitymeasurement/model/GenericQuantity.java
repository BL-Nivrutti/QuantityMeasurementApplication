package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.Unit;

import java.util.Objects;

/**
 * UC10 - Generic Quantity Class with Unit Interface
 *
 * <p>
 * A fully generic, type-safe quantity class that works with any unit
 * implementing the {@link Unit} interface. Applies generic programming,
 * interface-based design, OCP, LSP, composition over inheritance,
 * immutability, and runtime type checking.
 * </p>
 *
 * <p>
 * This replaces the separate {@code Quantity} and {@code WeightQuantity}
 * classes with a single, reusable abstraction.
 * </p>
 *
 * @param <U> the type of unit, must implement {@link Unit}
 * @author Nivrutti
 * @version 1.0.0
 */
public class GenericQuantity<U extends Unit> {

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-6;

    /** The numeric value of this measurement */
    private final double value;

    /** The unit of this measurement */
    private final U unit;

    /**
     * Constructs a GenericQuantity with the given value and unit.
     *
     * @param value the numeric measurement value (must be non-negative)
     * @param unit  the unit of measurement (must not be null)
     * @throws IllegalArgumentException if value is negative
     * @throws NullPointerException     if unit is null
     */
    public GenericQuantity(double value, U unit) {
        if (value < 0) {
            throw new IllegalArgumentException("Quantity value cannot be negative: " + value);
        }
        this.unit = Objects.requireNonNull(unit, "Unit must not be null");
        this.value = value;
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
    public U getUnit() {
        return unit;
    }

    /**
     * Converts this quantity to the base unit.
     *
     * @return value in base unit
     */
    public double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    /**
     * Converts this quantity to the specified target unit.
     *
     * @param targetUnit the unit to convert to (must not be null, must be same
     *                   category)
     * @return a new GenericQuantity in the target unit
     * @throws NullPointerException     if targetUnit is null
     * @throws IllegalArgumentException if targetUnit is from a different category
     */
    public GenericQuantity<U> convertTo(U targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit must not be null");
        // Runtime type check: ensure same enum type (same category)
        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException(
                    "Cannot convert between different unit categories: "
                            + unit.getClass().getSimpleName()
                            + " -> " + targetUnit.getClass().getSimpleName());
        }
        double baseValue = toBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new GenericQuantity<>(convertedValue, targetUnit);
    }

    /**
     * Adds another quantity of the same unit type and returns the result in this
     * unit.
     *
     * @param other the quantity to add (must not be null, must be same category)
     * @return a new GenericQuantity representing the sum in this unit
     */
    public GenericQuantity<U> add(GenericQuantity<U> other) {
        Objects.requireNonNull(other, "Other quantity must not be null");
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();
        double resultValue = sumInBase / this.unit.getConversionFactor();
        return new GenericQuantity<>(resultValue, this.unit);
    }

    /**
     * Checks equality by comparing base-unit values within epsilon tolerance.
     *
     * @param obj the object to compare with
     * @return {@code true} if both quantities represent the same physical
     *         measurement
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof GenericQuantity<?>))
            return false;
        GenericQuantity<?> other = (GenericQuantity<?>) obj;
        // Only equal if same unit category
        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    /**
     * Returns a hash code consistent with the equals contract.
     *
     * @return hash code based on unit class and base-unit value
     */
    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass().getName(),
                Math.round(toBaseUnit() / EPSILON) * EPSILON);
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
