package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.WeightUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC9 - Weight Measurement Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC9 - Weight Measurement")
class WeightQuantityTest {

    private static final double DELTA = 1e-3;

    @Test
    @DisplayName("Should return true when 1 kg equals 1000 grams")
    void shouldReturnTrueWhen1KgEquals1000Grams() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        WeightQuantity grams = new WeightQuantity(1000.0, WeightUnit.GRAM);
        assertEquals(kg, grams);
    }

    @Test
    @DisplayName("Should return false when different weights")
    void shouldReturnFalseWhenDifferentWeights() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        WeightQuantity grams = new WeightQuantity(500.0, WeightUnit.GRAM);
        assertNotEquals(kg, grams);
    }

    @Test
    @DisplayName("Should convert 1 kg to 1000 grams")
    void shouldConvert1KgTo1000Grams() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        WeightQuantity result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 1 tonne to 1000 kg")
    void shouldConvert1TonneTo1000Kg() {
        WeightQuantity tonne = new WeightQuantity(1.0, WeightUnit.TONNE);
        WeightQuantity result = tonne.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add 1 kg and 500 grams to get 1.5 kg")
    void shouldAdd1KgAnd500GramsTo1Point5Kg() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        WeightQuantity grams = new WeightQuantity(500.0, WeightUnit.GRAM);
        WeightQuantity result = kg.add(grams);
        assertEquals(1.5, result.getValue(), DELTA);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    @DisplayName("Should throw exception for negative weight value")
    void shouldThrowExceptionForNegativeWeightValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new WeightQuantity(-1.0, WeightUnit.GRAM));
    }

    @Test
    @DisplayName("Should throw exception for null unit")
    void shouldThrowExceptionForNullUnit() {
        assertThrows(NullPointerException.class,
                () -> new WeightQuantity(1.0, null));
    }

    @Test
    @DisplayName("Should have same hashCode for equal weights")
    void shouldHaveSameHashCodeForEqualWeights() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        WeightQuantity grams = new WeightQuantity(1000.0, WeightUnit.GRAM);
        assertEquals(kg.hashCode(), grams.hashCode());
    }

    @Test
    @DisplayName("Should return true when comparing object with itself")
    void shouldReturnTrueWhenComparingObjectWithItself() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        assertEquals(kg, kg);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void shouldReturnFalseWhenComparingWithNull() {
        WeightQuantity kg = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(kg, null);
    }
}
