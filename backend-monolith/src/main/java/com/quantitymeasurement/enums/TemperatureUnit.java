package com.quantitymeasurement.enums;

/**
 * UC14 - Temperature Measurement
 *
 * <p>
 * Enum representing temperature units. Temperature conversion is
 * non-linear (unlike length/weight/volume), so this enum does NOT
 * implement the {@link Unit} interface. Instead, it provides explicit
 * conversion methods between Celsius, Fahrenheit, and Kelvin.
 * </p>
 *
 * <p>
 * Demonstrates: non-linear conversions, ISP (not forcing Unit interface
 * on temperature), selective arithmetic support, exception semantics,
 * and capability-based design.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public enum TemperatureUnit {

    /** Celsius temperature unit */
    CELSIUS,

    /** Fahrenheit temperature unit */
    FAHRENHEIT,

    /** Kelvin temperature unit */
    KELVIN;

    /**
     * Converts a temperature value from this unit to the target unit.
     *
     * @param value      the temperature value in this unit
     * @param targetUnit the unit to convert to (must not be null)
     * @return the converted temperature value
     * @throws NullPointerException if targetUnit is null
     */
    public double convertTo(double value, TemperatureUnit targetUnit) {
        if (targetUnit == null)
            throw new NullPointerException("Target unit must not be null");
        if (this == targetUnit)
            return value;

        // First convert to Celsius as intermediate
        double celsius = toCelsius(value);

        // Then convert from Celsius to target
        return fromCelsius(celsius, targetUnit);
    }

    /**
     * Converts a value in this unit to Celsius.
     *
     * @param value the temperature value in this unit
     * @return the value in Celsius
     */
    private double toCelsius(double value) {
        return switch (this) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32.0) * 5.0 / 9.0;
            case KELVIN -> value - 273.15;
        };
    }

    /**
     * Converts a Celsius value to the target unit.
     *
     * @param celsius    the temperature in Celsius
     * @param targetUnit the target unit
     * @return the value in the target unit
     */
    private static double fromCelsius(double celsius, TemperatureUnit targetUnit) {
        return switch (targetUnit) {
            case CELSIUS -> celsius;
            case FAHRENHEIT -> (celsius * 9.0 / 5.0) + 32.0;
            case KELVIN -> celsius + 273.15;
        };
    }
}
