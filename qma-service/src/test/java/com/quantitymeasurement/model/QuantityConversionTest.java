package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC5 - Unit-to-Unit Conversion Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC5 - Unit-to-Unit Conversion")
class QuantityConversionTest {

    private static final double DELTA = 1e-4;

    @Test
    @DisplayName("Should convert 1 foot to 12 inches")
    void shouldConvert1FootTo12Inches() {
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = feet.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Should convert 12 inches to 1 foot")
    void shouldConvert12InchesTo1Foot() {
        Quantity inches = new Quantity(12.0, LengthUnit.INCH);
        Quantity result = inches.convertTo(LengthUnit.FEET);
        assertEquals(1.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Should convert 1 yard to 3 feet")
    void shouldConvert1YardTo3Feet() {
        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity result = yard.convertTo(LengthUnit.FEET);
        assertEquals(3.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 1 yard to 36 inches")
    void shouldConvert1YardTo36Inches() {
        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity result = yard.convertTo(LengthUnit.INCH);
        assertEquals(36.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 3 feet to 1 yard")
    void shouldConvert3FeetTo1Yard() {
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);
        Quantity result = feet.convertTo(LengthUnit.YARD);
        assertEquals(1.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should return same value when converting to same unit")
    void shouldReturnSameValueWhenConvertingToSameUnit() {
        Quantity feet = new Quantity(5.0, LengthUnit.FEET);
        Quantity result = feet.convertTo(LengthUnit.FEET);
        assertEquals(5.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should return correct value using getValueIn")
    void shouldReturnCorrectValueUsingGetValueIn() {
        Quantity feet = new Quantity(2.0, LengthUnit.FEET);
        assertEquals(24.0, feet.getValueIn(LengthUnit.INCH), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when converting to null unit")
    void shouldThrowExceptionWhenConvertingToNullUnit() {
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        assertThrows(NullPointerException.class, () -> feet.convertTo(null));
    }

    @Test
    @DisplayName("Converted quantity should be equal to original")
    void convertedQuantityShouldBeEqualToOriginal() {
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity inches = feet.convertTo(LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("Should produce immutable result on conversion")
    void shouldProduceImmutableResultOnConversion() {
        Quantity original = new Quantity(1.0, LengthUnit.FEET);
        Quantity converted = original.convertTo(LengthUnit.INCH);
        // original should be unchanged
        assertEquals(1.0, original.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, original.getUnit());
        // converted should be new object
        assertNotSame(original, converted);
    }
}
