package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC7 - Addition with Target Unit Specification Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC7 - Addition with Target Unit Specification")
class QuantityAdditionServiceTest {

    private static final double DELTA = 1e-4;
    private QuantityAdditionService service;

    @BeforeEach
    void setUp() {
        service = new QuantityAdditionService();
    }

    @Test
    @DisplayName("Should add 1 foot and 1 foot and return result in feet")
    void shouldAdd1FootAnd1FootReturnInFeet() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = service.add(q1, q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Should add 1 foot and 12 inches and return result in feet")
    void shouldAdd1FootAnd12InchesReturnInFeet() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        Quantity result = service.add(q1, q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should add 1 yard and 1 foot and return result in yards")
    void shouldAdd1YardAnd1FootReturnInYards() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = service.add(q1, q2, LengthUnit.YARD);
        assertEquals(1.333, result.getValue(), 1e-3);
    }

    @Test
    @DisplayName("Should add multiple quantities using addAll")
    void shouldAddMultipleQuantitiesUsingAddAll() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        Quantity q3 = new Quantity(1.0, LengthUnit.YARD);
        Quantity result = service.addAll(LengthUnit.INCH, q1, q2, q3);
        // 12 + 12 + 36 = 60 inches
        assertEquals(60.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should return default result in inches when no target unit given")
    void shouldReturnDefaultResultInInches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = service.add(q1, q2);
        assertEquals(LengthUnit.INCH, result.getUnit());
        assertEquals(24.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when addAll called with no quantities")
    void shouldThrowExceptionWhenAddAllCalledWithNoQuantities() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addAll(LengthUnit.INCH));
    }

    @Test
    @DisplayName("Should throw exception when null quantity in addAll")
    void shouldThrowExceptionWhenNullQuantityInAddAll() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        assertThrows(NullPointerException.class,
                () -> service.addAll(LengthUnit.INCH, q1, null));
    }
}
