package com.quantitymeasurement.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC1 - Feet Measurement Equality Tests
 *
 * <p>
 * Tests for object equality, floating-point comparison, null checking,
 * type checking, and OOP design validation.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC1 - Feet Measurement Equality")
class FeetTest {

    // ─── Equality Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return true when comparing same feet value")
    void shouldReturnTrueWhenComparingSameFeetValue() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        assertEquals(feet1, feet2);
    }

    @Test
    @DisplayName("Should return false when comparing different feet values")
    void shouldReturnFalseWhenComparingDifferentFeetValues() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);
        assertNotEquals(feet1, feet2);
    }

    @Test
    @DisplayName("Should return true when comparing zero feet values")
    void shouldReturnTrueWhenComparingZeroFeetValues() {
        Feet feet1 = new Feet(0.0);
        Feet feet2 = new Feet(0.0);
        assertEquals(feet1, feet2);
    }

    // ─── Null Checking Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("Should return false when comparing with null")
    void shouldReturnFalseWhenComparingWithNull() {
        Feet feet = new Feet(1.0);
        assertNotEquals(feet, null);
    }

    // ─── Type Checking Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("Should return false when comparing with different type")
    void shouldReturnFalseWhenComparingWithDifferentType() {
        Feet feet = new Feet(1.0);
        assertNotEquals(feet, "1.0");
    }

    @Test
    @DisplayName("Should return false when comparing with integer")
    void shouldReturnFalseWhenComparingWithInteger() {
        Feet feet = new Feet(1.0);
        assertNotEquals(feet, 1);
    }

    // ─── Self Reference Test ──────────────────────────────────────────────────

    @Test
    @DisplayName("Should return true when comparing object with itself")
    void shouldReturnTrueWhenComparingObjectWithItself() {
        Feet feet = new Feet(1.0);
        assertEquals(feet, feet);
    }

    // ─── Floating Point Precision Tests ───────────────────────────────────────

    @Test
    @DisplayName("Should handle floating point precision correctly")
    void shouldHandleFloatingPointPrecisionCorrectly() {
        Feet feet1 = new Feet(1.0000000001);
        Feet feet2 = new Feet(1.0000000002);
        assertEquals(feet1, feet2); // within epsilon tolerance
    }

    @Test
    @DisplayName("Should return false for clearly different floating point values")
    void shouldReturnFalseForClearlyDifferentFloatingPointValues() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.1);
        assertNotEquals(feet1, feet2);
    }

    // ─── Validation Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw exception for negative feet value")
    void shouldThrowExceptionForNegativeFeetValue() {
        assertThrows(IllegalArgumentException.class, () -> new Feet(-1.0));
    }

    // ─── HashCode Contract Tests ──────────────────────────────────────────────

    @Test
    @DisplayName("Should have same hashCode for equal objects")
    void shouldHaveSameHashCodeForEqualObjects() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        assertEquals(feet1.hashCode(), feet2.hashCode());
    }

    // ─── ToString Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation() {
        Feet feet = new Feet(1.0);
        assertEquals("1.0 feet", feet.toString());
    }

    // ─── GetValue Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Should return correct value")
    void shouldReturnCorrectValue() {
        Feet feet = new Feet(5.0);
        assertEquals(5.0, feet.getValue());
    }
}
