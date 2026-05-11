package com.quantitymeasurement.enums;

/**
 * UC8 - Refactored LengthUnit as Standalone Enum
 *
 * <p>
 * Implements the {@link Unit} interface. Conversion logic is fully
 * encapsulated within this enum, following SRP and the delegation pattern.
 * Supports FEET, INCH, YARD, and CENTIMETER.
 * </p>
 *
 * @author Nivrutti
 * @version 3.0.0
 */
public enum LengthUnit implements Unit {

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

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }
}
