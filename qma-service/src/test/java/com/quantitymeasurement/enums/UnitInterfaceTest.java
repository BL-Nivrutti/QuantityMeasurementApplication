package com.quantitymeasurement.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC8 - Refactor Unit Enum to Standalone Architecture Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC8 - Refactor Unit Enum to Standalone Architecture")
class UnitInterfaceTest {

    @Test
    @DisplayName("LengthUnit should implement Unit interface")
    void lengthUnitShouldImplementUnitInterface() {
        Unit unit = LengthUnit.FEET;
        assertNotNull(unit);
        assertInstanceOf(Unit.class, unit);
    }

    @Test
    @DisplayName("LengthUnit FEET should have conversion factor 12")
    void lengthUnitFeetShouldHaveConversionFactor12() {
        assertEquals(12.0, LengthUnit.FEET.getConversionFactor(), 1e-9);
    }

    @Test
    @DisplayName("LengthUnit INCH should have conversion factor 1")
    void lengthUnitInchShouldHaveConversionFactor1() {
        assertEquals(1.0, LengthUnit.INCH.getConversionFactor(), 1e-9);
    }

    @Test
    @DisplayName("LengthUnit YARD should have conversion factor 36")
    void lengthUnitYardShouldHaveConversionFactor36() {
        assertEquals(36.0, LengthUnit.YARD.getConversionFactor(), 1e-9);
    }

    @Test
    @DisplayName("Unit interface name() should return enum name")
    void unitInterfaceNameShouldReturnEnumName() {
        Unit unit = LengthUnit.FEET;
        assertEquals("FEET", unit.name());
    }

    @Test
    @DisplayName("All LengthUnit values should implement Unit interface")
    void allLengthUnitValuesShouldImplementUnitInterface() {
        for (LengthUnit lu : LengthUnit.values()) {
            assertInstanceOf(Unit.class, lu);
            assertTrue(lu.getConversionFactor() > 0,
                    "Conversion factor must be positive for: " + lu.name());
        }
    }
}
