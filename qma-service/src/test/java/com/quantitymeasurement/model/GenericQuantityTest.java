package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.WeightUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC10 - Generic Quantity Class with Unit Interface Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC10 - Generic Quantity Class with Unit Interface")
class GenericQuantityTest {

    private static final double DELTA = 1e-4;

    @Test
    @DisplayName("Should work with LengthUnit - 1 foot equals 12 inches")
    void shouldWorkWithLengthUnit1FootEquals12Inches() {
        GenericQuantity<LengthUnit> feet = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> inches = new GenericQuantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("Should work with WeightUnit - 1 kg equals 1000 grams")
    void shouldWorkWithWeightUnit1KgEquals1000Grams() {
        GenericQuantity<WeightUnit> kg = new GenericQuantity<>(1.0, WeightUnit.KILOGRAM);
        GenericQuantity<WeightUnit> grams = new GenericQuantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, grams);
    }

    @Test
    @DisplayName("Should convert length units correctly")
    void shouldConvertLengthUnitsCorrectly() {
        GenericQuantity<LengthUnit> feet = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> result = feet.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add two length quantities")
    void shouldAddTwoLengthQuantities() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = q1.add(q2);
        assertEquals(24.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should add two weight quantities")
    void shouldAddTwoWeightQuantities() {
        GenericQuantity<WeightUnit> kg = new GenericQuantity<>(1.0, WeightUnit.KILOGRAM);
        GenericQuantity<WeightUnit> grams = new GenericQuantity<>(500.0, WeightUnit.GRAM);
        GenericQuantity<WeightUnit> result = kg.add(grams);
        assertEquals(1.5, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception for negative value")
    void shouldThrowExceptionForNegativeValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new GenericQuantity<>(-1.0, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Should throw exception for null unit")
    void shouldThrowExceptionForNullUnit() {
        assertThrows(NullPointerException.class,
                () -> new GenericQuantity<>(1.0, null));
    }

    @Test
    @DisplayName("Length and weight quantities should not be equal")
    void lengthAndWeightQuantitiesShouldNotBeEqual() {
        GenericQuantity<LengthUnit> length = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<WeightUnit> weight = new GenericQuantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length, weight);
    }

    @Test
    @DisplayName("Should have same hashCode for equal quantities")
    void shouldHaveSameHashCodeForEqualQuantities() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        assertEquals(q1.hashCode(), q2.hashCode());
    }
}
