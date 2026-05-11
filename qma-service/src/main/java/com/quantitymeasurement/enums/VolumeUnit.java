package com.quantitymeasurement.enums;

/**
 * UC11 - Volume Measurement
 *
 * <p>
 * Enum representing volume units with their conversion factors to a
 * common base unit (millilitres). Implements the {@link Unit} interface.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public enum VolumeUnit implements Unit {

    /** Litre unit: 1 litre = 1000 millilitres */
    LITRE(1000.0),

    /** Millilitre unit: 1 ml = 1 ml (base unit) */
    MILLILITRE(1.0),

    /** Gallon unit: 1 gallon ≈ 3785.41 millilitres */
    GALLON(3785.41),

    /** Cup unit: 1 cup ≈ 236.588 millilitres */
    CUP(236.588);

    /** Conversion factor to base unit (millilitres) */
    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
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
