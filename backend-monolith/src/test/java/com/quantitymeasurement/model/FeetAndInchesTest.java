package com.quantitymeasurement.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC2 - Feet and Inches Measurement Equality Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC2 - Feet and Inches Measurement Equality")
class FeetAndInchesTest {

    @Test
    @DisplayName("Should return true when 1 foot equals 12 inches")
    void shouldReturnTrueWhen1FootEquals12Inches() {
        FeetAndInches m1 = new FeetAndInches(1.0, 0.0);
        FeetAndInches m2 = new FeetAndInches(0.0, 12.0);
        assertEquals(m1, m2);
    }

    @Test
    @DisplayName("Should return true when same feet and inches")
    void shouldReturnTrueWhenSameFeetAndInches() {
        FeetAndInches m1 = new FeetAndInches(2.0, 6.0);
        FeetAndInches m2 = new FeetAndInches(2.0, 6.0);
        assertEquals(m1, m2);
    }

    @Test
    @DisplayName("Should return false when different measurements")
    void shouldReturnFalseWhenDifferentMeasurements() {
        FeetAndInches m1 = new FeetAndInches(1.0, 0.0);
        FeetAndInches m2 = new FeetAndInches(1.0, 6.0);
        assertNotEquals(m1, m2);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void shouldReturnFalseWhenComparingWithNull() {
        FeetAndInches m = new FeetAndInches(1.0, 0.0);
        assertNotEquals(m, null);
    }

    @Test
    @DisplayName("Should correctly compute total inches")
    void shouldCorrectlyComputeTotalInches() {
        FeetAndInches m = new FeetAndInches(1.0, 6.0);
        assertEquals(18.0, m.toTotalInches(), 1e-9);
    }

    @Test
    @DisplayName("Should correctly compute total feet")
    void shouldCorrectlyComputeTotalFeet() {
        FeetAndInches m = new FeetAndInches(0.0, 24.0);
        assertEquals(2.0, m.toTotalFeet(), 1e-9);
    }

    @Test
    @DisplayName("Should throw exception for negative feet")
    void shouldThrowExceptionForNegativeFeet() {
        assertThrows(IllegalArgumentException.class, () -> new FeetAndInches(-1.0, 0.0));
    }

    @Test
    @DisplayName("Should throw exception for negative inches")
    void shouldThrowExceptionForNegativeInches() {
        assertThrows(IllegalArgumentException.class, () -> new FeetAndInches(0.0, -1.0));
    }

    @Test
    @DisplayName("Should return true when comparing object with itself")
    void shouldReturnTrueWhenComparingObjectWithItself() {
        FeetAndInches m = new FeetAndInches(1.0, 6.0);
        assertEquals(m, m);
    }

    @Test
    @DisplayName("Should have same hashCode for equal objects")
    void shouldHaveSameHashCodeForEqualObjects() {
        FeetAndInches m1 = new FeetAndInches(1.0, 0.0);
        FeetAndInches m2 = new FeetAndInches(0.0, 12.0);
        assertEquals(m1.hashCode(), m2.hashCode());
    }
}
