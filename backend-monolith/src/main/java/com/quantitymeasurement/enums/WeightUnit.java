package com.quantitymeasurement.enums;

/**
 * UC9 - Weight Measurement
 *
 * <p>
 * Enum representing weight units with their conversion factors to a
 * common base unit (grams). Implements the {@link Unit} interface for
 * consistent behavior across all measurement categories.
 * </p>
 *
 * <p>
 * Demonstrates: multi-category measurements, base unit normalization,
 * arithmetic support, floating-point precision consistency.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public enum WeightUnit implements Unit {

    /** Kilogram unit: 1 kg = 1000 grams (base unit) */
    KILOGRAM(1000.0),

    /** Gram unit: 1 gram = 1 gram (base unit) */
    GRAM(1.0),

    /** Tonne unit: 1 tonne = 1,000,000 grams */
    TONNE(1_000_000.0),

    /** Pound unit: 1 pound ≈ 453.592 grams */
    POUND(453.592),

    /** Ounce unit: 1 ounce ≈ 28.3495 grams */
    OUNCE(28.3495);

    /** Conversion factor to base unit (grams) */
    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
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
