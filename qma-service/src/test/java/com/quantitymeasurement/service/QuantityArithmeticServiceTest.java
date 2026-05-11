package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.WeightUnit;
import com.quantitymeasurement.exception.QuantityArithmeticException;
import com.quantitymeasurement.model.GenericQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC12 - Subtraction and Division Operations Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC12 - Subtraction and Division Operations")
class QuantityArithmeticServiceTest {

    private static final double DELTA = 1e-4;
    private QuantityArithmeticService service;

    @BeforeEach
    void setUp() {
        service = new QuantityArithmeticService();
    }

    // ─── Addition ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should add 1 foot and 12 inches to get 24 inches")
    void shouldAdd1FootAnd12InchesTo24Inches() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.add(q1, q2);
        assertEquals(24.0, result.toBaseUnit(), DELTA);
    }

    // ─── Subtraction ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should subtract 6 inches from 1 foot to get 6 inches")
    void shouldSubtract6InchesFrom1FootTo6Inches() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(6.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.subtract(q1, q2);
        assertEquals(6.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should subtract equal quantities to get zero")
    void shouldSubtractEqualQuantitiesToGetZero() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.subtract(q1, q2);
        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when subtraction result is negative")
    void shouldThrowExceptionWhenSubtractionResultIsNegative() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        assertThrows(QuantityArithmeticException.class, () -> service.subtract(q1, q2));
    }

    // ─── Division ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should divide 2 feet by 1 foot to get ratio 2.0")
    void shouldDivide2FeetBy1FootToGetRatio2() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(2.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        assertEquals(2.0, service.divide(q1, q2), DELTA);
    }

    @Test
    @DisplayName("Should divide 1 foot by 12 inches to get ratio 1.0")
    void shouldDivide1FootBy12InchesToGetRatio1() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        assertEquals(1.0, service.divide(q1, q2), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero quantity")
    void shouldThrowExceptionWhenDividingByZeroQuantity() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(0.0, LengthUnit.INCH);
        assertThrows(QuantityArithmeticException.class, () -> service.divide(q1, q2));
    }

    @Test
    @DisplayName("Should divide by scalar correctly")
    void shouldDivideByScalarCorrectly() {
        GenericQuantity<LengthUnit> q = new GenericQuantity<>(10.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.divideByScalar(q, 2.0);
        assertEquals(5.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero scalar")
    void shouldThrowExceptionWhenDividingByZeroScalar() {
        GenericQuantity<LengthUnit> q = new GenericQuantity<>(1.0, LengthUnit.FEET);
        assertThrows(QuantityArithmeticException.class, () -> service.divideByScalar(q, 0.0));
    }

    @Test
    @DisplayName("Subtraction should be non-commutative")
    void subtractionShouldBeNonCommutative() {
        GenericQuantity<WeightUnit> q1 = new GenericQuantity<>(1.0, WeightUnit.KILOGRAM);
        GenericQuantity<WeightUnit> q2 = new GenericQuantity<>(500.0, WeightUnit.GRAM);
        GenericQuantity<WeightUnit> result = service.subtract(q1, q2);
        assertEquals(0.5, result.getValue(), DELTA);
        // reverse would throw exception
        assertThrows(QuantityArithmeticException.class, () -> service.subtract(q2, q1));
    }
}
