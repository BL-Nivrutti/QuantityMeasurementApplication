package com.quantitymeasurement.enums;

/**
 * UC4 - Extended Unit Support
 *
 * <p>
 * Enum representing length units with their conversion factors to a
 * common base unit (inches). Extended to support YARD and CENTIMETER.
 * Demonstrates enum extensibility, mathematical accuracy, DRY principle,
 * validation, and backward compatibility.
 * </p>
 *
 * @author Nivrutti
 * @version 2.0.0
 */
public enum LengthUnit {

    /** Feet unit: 1 foot = 12 inches */
    FEET(12.0),

    /** Inches unit: 1 inch = 1 inch (base unit) */
    INCH(1.0),

    /** Yard unit: 1 yard = 36 inches */
    YARD(36.0),

    /** Centimeter unit: 1 cm ≈ 0.393701 inches */
    CENTIMETER(0.393701);

    /** Conversion factor to base unit (inches) */
    private final double conversionFactor;

    /**
     * Constructs a LengthUnit with the given conversion factor.
     *
     * @param conversionFactor multiplier to convert this unit to inches
     */
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Returns the conversion factor to the base unit (inches).
     *
     * @return conversion factor
     */
    public double getConversionFactor() {
        return conversionFactor;
    }
}
