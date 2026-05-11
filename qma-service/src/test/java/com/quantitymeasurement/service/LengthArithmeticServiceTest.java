package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC6 - Addition of Two Length Units Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC6 - Addition of Two Length Units")
class LengthArithmeticServiceTest {

    private static final double DELTA = 1e-4;
    private LengthArithmeticService service;

    @BeforeEach
    void setUp() {
        service = new LengthArithmeticService();
    }

    @Test
    @DisplayName("Should add 2 inches and 2 inches to get 4 inches")
    void shouldAdd2InchesAnd2InchesTo4Inches() {
        Quantity q1 = new Quantity(2.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(2.0, LengthUnit.INCH);
        Quantity result = service.add(q1, q2);
        assertEquals(4.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Should add 1 foot and 1 foot to get 24 inches")
    void shouldAdd1FootAnd1FootTo24Inches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = service.add(q1, q2);
        assertEquals(24.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add 1 foot and 12 inches to get 24 inches")
    void shouldAdd1FootAnd12InchesTo24Inches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        Quantity result = service.add(q1, q2);
        assertEquals(24.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add 1 foot and 1 inch to get 13 inches")
    void shouldAdd1FootAnd1InchTo13Inches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.INCH);
        Quantity result = service.add(q1, q2);
        assertEquals(13.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add 1 yard and 1 foot to get 48 inches")
    void shouldAdd1YardAnd1FootTo48Inches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = service.add(q1, q2);
        assertEquals(48.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add and return result in feet")
    void shouldAddAndReturnResultInFeet() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        Quantity result = service.add(q1, q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Should add zero quantity without changing value")
    void shouldAddZeroQuantityWithoutChangingValue() {
        Quantity q1 = new Quantity(5.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(0.0, LengthUnit.INCH);
        Quantity result = service.add(q1, q2);
        assertEquals(5.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when first quantity is null")
    void shouldThrowExceptionWhenFirstQuantityIsNull() {
        Quantity q2 = new Quantity(1.0, LengthUnit.INCH);
        assertThrows(NullPointerException.class, () -> service.add(null, q2));
    }

    @Test
    @DisplayName("Should throw exception when second quantity is null")
    void shouldThrowExceptionWhenSecondQuantityIsNull() {
        Quantity q1 = new Quantity(1.0, LengthUnit.INCH);
        assertThrows(NullPointerException.class, () -> service.add(q1, null));
    }
}
