package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;

import java.util.Objects;

/**
 * UC5 - Unit-to-Unit Conversion
 *
 * <p>
 * Immutable value object representing a physical quantity with a numeric
 * value and a unit. Supports conversion between any two compatible units
 * via a shared base unit (inches). Implements value object semantics:
 * immutability, equals/hashCode contract, and meaningful toString.
 * </p>
 *
 * <p>
 * Demonstrates: conversion factors, immutability, JavaDocs,
 * private methods, method overriding, and method overloading.
 * </p>
 *
 * @author Nivrutti
 * @version 3.0.0
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
     * Converts this quantity to the specified target unit.
     *
     * <p>
     * Conversion is performed via the base unit:
     * {@code result = (value * sourceConversionFactor) / targetConversionFactor}
     * </p>
     *
     * @param targetUnit the unit to convert to (must not be null)
     * @return a new Quantity representing the same physical length in the target
     *         unit
     * @throws NullPointerException if targetUnit is null
     */
    public Quantity convertTo(LengthUnit targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit must not be null");
        double baseValue = toBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new Quantity(convertedValue, targetUnit);
    }

    /**
     * Converts this quantity to the specified target unit and returns the numeric
     * value.
     *
     * @param targetUnit the unit to convert to
     * @return the numeric value in the target unit
     */
    public double getValueIn(LengthUnit targetUnit) {
        return convertTo(targetUnit).getValue();
    }

    /**
     * Checks equality by comparing base-unit values within epsilon tolerance.
     *
     * @param obj the object to compare with
     * @return {@code true} if both quantities represent the same physical length
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
