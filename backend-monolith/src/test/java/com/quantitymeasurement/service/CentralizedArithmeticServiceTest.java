package com.quantitymeasurement.service;

import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.enums.Operation;
import com.quantitymeasurement.enums.WeightUnit;
import com.quantitymeasurement.exception.QuantityArithmeticException;
import com.quantitymeasurement.model.GenericQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC13 - Centralized Arithmetic Logic Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC13 - Centralized Arithmetic Logic")
class CentralizedArithmeticServiceTest {

    private static final double DELTA = 1e-4;
    private CentralizedArithmeticService service;

    @BeforeEach
    void setUp() {
        service = new CentralizedArithmeticService();
    }

    @Test
    @DisplayName("Should add using Operation.ADD")
    void shouldAddUsingOperationAdd() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.compute(q1, q2, Operation.ADD);
        assertEquals(24.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should subtract using Operation.SUBTRACT")
    void shouldSubtractUsingOperationSubtract() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(2.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(6.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.compute(q1, q2, Operation.SUBTRACT);
        assertEquals(18.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should multiply using Operation.MULTIPLY")
    void shouldMultiplyUsingOperationMultiply() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(2.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(3.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> result = service.compute(q1, q2, Operation.MULTIPLY);
        // 24 * 36 = 864 base units
        assertEquals(864.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should divide using Operation.DIVIDE - result is ratio in q1 unit")
    void shouldDivideUsingOperationDivide() {
        // 24 inches / 12 inches = 2.0 (ratio), stored as 2.0/12 feet = 0.1666 feet
        // Better to check toBaseUnit: (24/12) / 12 * 12 = 2.0 base
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(2.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> result = service.compute(q1, q2, Operation.DIVIDE);
        // 24 / 12 = 2.0 base units, then / 12 (feet factor) = 0.1666 feet
        // toBaseUnit = 0.1666 * 12 = 2.0
        assertEquals(2.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should compute with target unit")
    void shouldComputeWithTargetUnit() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(12.0, LengthUnit.INCH);
        GenericQuantity<LengthUnit> result = service.compute(q1, q2, Operation.ADD, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Should throw exception on division by zero via Operation.DIVIDE")
    void shouldThrowExceptionOnDivisionByZero() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(0.0, LengthUnit.INCH);
        assertThrows(QuantityArithmeticException.class,
                () -> service.compute(q1, q2, Operation.DIVIDE));
    }

    @Test
    @DisplayName("Should work with weight units via centralized service")
    void shouldWorkWithWeightUnitViaCentralizedService() {
        GenericQuantity<WeightUnit> q1 = new GenericQuantity<>(1.0, WeightUnit.KILOGRAM);
        GenericQuantity<WeightUnit> q2 = new GenericQuantity<>(500.0, WeightUnit.GRAM);
        GenericQuantity<WeightUnit> result = service.compute(q1, q2, Operation.ADD);
        assertEquals(1500.0, result.toBaseUnit(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception when null operation")
    void shouldThrowExceptionWhenNullOperation() {
        GenericQuantity<LengthUnit> q1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> q2 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        assertThrows(NullPointerException.class,
                () -> service.compute(q1, q2, null));
    }
}
