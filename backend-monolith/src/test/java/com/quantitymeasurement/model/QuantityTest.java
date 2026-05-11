package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.LengthUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC3 - Generic Quantity Class Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC3 - Generic Quantity Class")
class QuantityTest {

    @Test
    @DisplayName("Should return true when 1 foot equals 12 inches")
    void shouldReturnTrueWhen1FootEquals12Inches() {
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity inches = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("Should return true when same unit same value")
    void shouldReturnTrueWhenSameUnitSameValue() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(5.0, LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    @DisplayName("Should return false when different lengths")
    void shouldReturnFalseWhenDifferentLengths() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.INCH);
        assertNotEquals(q1, q2);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void shouldReturnFalseWhenComparingWithNull() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        assertNotEquals(q, null);
    }

    @Test
    @DisplayName("Should return false when comparing with different type")
    void shouldReturnFalseWhenComparingWithDifferentType() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        assertNotEquals(q, "1.0 feet");
    }

    @Test
    @DisplayName("Should return true when comparing object with itself")
    void shouldReturnTrueWhenComparingObjectWithItself() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test
    @DisplayName("Should throw exception for negative value")
    void shouldThrowExceptionForNegativeValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity(-1.0, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Should throw exception for null unit")
    void shouldThrowExceptionForNullUnit() {
        assertThrows(NullPointerException.class,
                () -> new Quantity(1.0, null));
    }

    @Test
    @DisplayName("Should correctly convert feet to base unit")
    void shouldCorrectlyConvertFeetToBaseUnit() {
        Quantity q = new Quantity(2.0, LengthUnit.FEET);
        assertEquals(24.0, q.toBaseUnit(), 1e-9);
    }

    @Test
    @DisplayName("Should have same hashCode for equal quantities")
    void shouldHaveSameHashCodeForEqualQuantities() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(q1.hashCode(), q2.hashCode());
    }
}
