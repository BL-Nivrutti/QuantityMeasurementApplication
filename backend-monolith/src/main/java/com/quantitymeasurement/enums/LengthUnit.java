package com.quantitymeasurement.enums;

/**
 * UC3 - Generic Quantity Class
 *
 * <p>
 * Enum representing length units with their conversion factors to a
 * common base unit (inches). Supports DRY principle by centralizing
 * conversion logic within the enum itself.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public enum LengthUnit {

    /** Feet unit: 1 foot = 12 inches */
    FEET(12.0),

    /** Inches unit: 1 inch = 1 inch (base unit) */
    INCH(1.0);

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
