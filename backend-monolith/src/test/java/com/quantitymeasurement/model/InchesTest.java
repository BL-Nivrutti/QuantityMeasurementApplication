package com.quantitymeasurement.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC2 - Inches Measurement Equality Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC2 - Inches Measurement Equality")
class InchesTest {

    @Test
    @DisplayName("Should return true when comparing same inches value")
    void shouldReturnTrueWhenComparingSameInchesValue() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(12.0);
        assertEquals(i1, i2);
    }

    @Test
    @DisplayName("Should return false when comparing different inches values")
    void shouldReturnFalseWhenComparingDifferentInchesValues() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(24.0);
        assertNotEquals(i1, i2);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void shouldReturnFalseWhenComparingWithNull() {
        Inches i = new Inches(12.0);
        assertNotEquals(i, null);
    }

    @Test
    @DisplayName("Should return false when comparing with different type")
    void shouldReturnFalseWhenComparingWithDifferentType() {
        Inches i = new Inches(12.0);
        assertNotEquals(i, "12.0");
    }

    @Test
    @DisplayName("Should correctly convert inches to feet")
    void shouldCorrectlyConvertInchesToFeet() {
        Inches i = new Inches(12.0);
        assertEquals(1.0, i.toFeet(), 1e-9);
    }

    @Test
    @DisplayName("Should throw exception for negative inches value")
    void shouldThrowExceptionForNegativeInchesValue() {
        assertThrows(IllegalArgumentException.class, () -> new Inches(-1.0));
    }

    @Test
    @DisplayName("Should have same hashCode for equal objects")
    void shouldHaveSameHashCodeForEqualObjects() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(12.0);
        assertEquals(i1.hashCode(), i2.hashCode());
    }
}
