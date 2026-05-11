package com.quantitymeasurement.enums;

import com.quantitymeasurement.model.GenericQuantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC11 - Volume Measurement Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC11 - Volume Measurement")
class VolumeUnitTest {

    private static final double DELTA = 1e-2;

    @Test
    @DisplayName("Should return true when 1 litre equals 1000 millilitres")
    void shouldReturnTrueWhen1LitreEquals1000Millilitres() {
        GenericQuantity<VolumeUnit> litre = new GenericQuantity<>(1.0, VolumeUnit.LITRE);
        GenericQuantity<VolumeUnit> ml = new GenericQuantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(litre, ml);
    }

    @Test
    @DisplayName("Should convert 1 gallon to millilitres")
    void shouldConvert1GallonToMillilitres() {
        GenericQuantity<VolumeUnit> gallon = new GenericQuantity<>(1.0, VolumeUnit.GALLON);
        GenericQuantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(3785.41, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 1 gallon to litres")
    void shouldConvert1GallonToLitres() {
        GenericQuantity<VolumeUnit> gallon = new GenericQuantity<>(1.0, VolumeUnit.GALLON);
        GenericQuantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(3.785, result.getValue(), 1e-2);
    }

    @Test
    @DisplayName("Should add 1 litre and 1000 ml to get 2 litres")
    void shouldAdd1LitreAnd1000MlTo2Litres() {
        GenericQuantity<VolumeUnit> litre = new GenericQuantity<>(1.0, VolumeUnit.LITRE);
        GenericQuantity<VolumeUnit> ml = new GenericQuantity<>(1000.0, VolumeUnit.MILLILITRE);
        GenericQuantity<VolumeUnit> result = litre.add(ml);
        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("Should return false when different volumes")
    void shouldReturnFalseWhenDifferentVolumes() {
        GenericQuantity<VolumeUnit> litre = new GenericQuantity<>(1.0, VolumeUnit.LITRE);
        GenericQuantity<VolumeUnit> ml = new GenericQuantity<>(500.0, VolumeUnit.MILLILITRE);
        assertNotEquals(litre, ml);
    }

    @Test
    @DisplayName("VolumeUnit should implement Unit interface")
    void volumeUnitShouldImplementUnitInterface() {
        assertInstanceOf(Unit.class, VolumeUnit.LITRE);
    }

    @Test
    @DisplayName("Should have 4 volume units defined")
    void shouldHave4VolumeUnitsDefined() {
        assertEquals(4, VolumeUnit.values().length);
    }
}
