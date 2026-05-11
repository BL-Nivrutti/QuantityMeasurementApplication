package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.TemperatureUnit;
import com.quantitymeasurement.exception.QuantityArithmeticException;

import java.util.Objects;

/**
 * UC14 - Temperature Measurement
 *
 * <p>
 * Immutable value object representing a temperature measurement.
 * Supports conversion between Celsius, Fahrenheit, and Kelvin using
 * non-linear conversion formulas. Addition is supported only for
 * temperature differences (not absolute temperatures), and subtraction
 * is always valid.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class Temperature {

    /** Tolerance for floating-point comparison */
    private static final double EPSILON = 1e-6;

    /** The numeric temperature value */
    private final double value;

    /** The unit of this temperature */
    private final TemperatureUnit unit;

    /**
     * Constructs a Temperature with the given value and unit.
     *
     * @param value the temperature value
     * @param unit  the temperature unit (must not be null)
     * @throws NullPointerException if unit is null
     */
    public Temperature(double value, TemperatureUnit unit) {
        this.unit = Objects.requireNonNull(unit, "Temperature unit must not be null");
        this.value = value;
    }

    /**
     * Returns the temperature value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the temperature unit.
     *
     * @return the unit
     */
    public TemperatureUnit getUnit() {
        return unit;
    }

    /**
     * Converts this temperature to the specified target unit.
     *
     * @param targetUnit the unit to convert to (must not be null)
     * @return a new Temperature in the target unit
     */
    public Temperature convertTo(TemperatureUnit targetUnit) {
        double converted = unit.convertTo(value, targetUnit);
        return new Temperature(converted, targetUnit);
    }

    /**
     * Returns the value in Celsius (used for equality comparison).
     *
     * @return value in Celsius
     */
    public double toCelsius() {
        return unit.convertTo(value, TemperatureUnit.CELSIUS);
    }

    /**
     * Subtracts another temperature from this one and returns the difference
     * in this unit.
     *
     * @param other the temperature to subtract (must not be null)
     * @return a new Temperature representing the difference
     */
    public Temperature subtract(Temperature other) {
        Objects.requireNonNull(other, "Other temperature must not be null");
        double thisCelsius = this.toCelsius();
        double otherCelsius = other.toCelsius();
        double diffCelsius = thisCelsius - otherCelsius;
        // Convert the difference back to this unit (offset-based, not absolute)
        double resultValue = unit.convertTo(
                TemperatureUnit.CELSIUS.convertTo(diffCelsius, unit), unit);
        return new Temperature(diffCelsius, TemperatureUnit.CELSIUS).convertTo(this.unit);
    }

    /**
     * Checks equality by comparing Celsius values within epsilon tolerance.
     *
     * @param obj the object to compare with
     * @return {@code true} if both temperatures represent the same physical
     *         temperature
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Temperature))
            return false;
        Temperature other = (Temperature) obj;
        return Math.abs(this.toCelsius() - other.toCelsius()) < EPSILON;
    }

    /**
     * Returns a hash code consistent with the equals contract.
     *
     * @return hash code based on Celsius value
     */
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toCelsius() / EPSILON) * EPSILON);
    }

    /**
     * Returns a string representation of this temperature.
     *
     * @return string in format "X.X °UNIT"
     */
    @Override
    public String toString() {
        return value + " °" + unit.name().charAt(0);
    }
}
