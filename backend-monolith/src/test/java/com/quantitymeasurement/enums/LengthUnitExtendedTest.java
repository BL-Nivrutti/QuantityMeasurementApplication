package com.quantitymeasurement.enums;

import com.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC4 - Extended Unit Support Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC4 - Extended Unit Support")
class LengthUnitExtendedTest {

    private static final double DELTA = 1e-4;

    @Test
    @DisplayName("Should return true when 1 yard equals 3 feet")
    void shouldReturnTrueWhen1YardEquals3Feet() {
        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test
    @DisplayName("Should return true when 1 yard equals 36 inches")
    void shouldReturnTrueWhen1YardEquals36Inches() {
        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity inches = new Quantity(36.0, LengthUnit.INCH);
        assertEquals(yard, inches);
    }

    @Test
    @DisplayName("Should correctly compute yard conversion factor")
    void shouldCorrectlyComputeYardConversionFactor() {
        assertEquals(36.0, LengthUnit.YARD.getConversionFactor(), DELTA);
    }

    @Test
    @DisplayName("Should correctly compute centimeter conversion factor")
    void shouldCorrectlyComputeCentimeterConversionFactor() {
        assertEquals(0.393701, LengthUnit.CENTIMETER.getConversionFactor(), DELTA);
    }

    @Test
    @DisplayName("Should correctly convert centimeters to inches base unit")
    void shouldCorrectlyConvertCentimetersToInchesBaseUnit() {
        // 1 inch = 2.54 cm exactly; using conversion factor 0.393701
        // 2.54 cm * 0.393701 = 0.99999954 inches (within 1e-4 of 1.0)
        Quantity cm = new Quantity(2.54, LengthUnit.CENTIMETER);
        assertEquals(1.0, cm.toBaseUnit(), 1e-4);
    }

    @Test
    @DisplayName("Should maintain backward compatibility with FEET and INCH")
    void shouldMaintainBackwardCompatibilityWithFeetAndInch() {
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity inches = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("Should return false when different lengths in different units")
    void shouldReturnFalseWhenDifferentLengthsInDifferentUnits() {
        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity feet = new Quantity(2.0, LengthUnit.FEET);
        assertNotEquals(yard, feet);
    }

    @Test
    @DisplayName("Should have 4 length units defined")
    void shouldHave4LengthUnitsDefined() {
        assertEquals(4, LengthUnit.values().length);
    }
}
