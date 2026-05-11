package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.TemperatureUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC14 - Temperature Measurement Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC14 - Temperature Measurement")
class TemperatureTest {

    private static final double DELTA = 1e-3;

    @Test
    @DisplayName("Should return true when 0 Celsius equals 32 Fahrenheit")
    void shouldReturnTrueWhen0CelsiusEquals32Fahrenheit() {
        Temperature celsius = new Temperature(0.0, TemperatureUnit.CELSIUS);
        Temperature fahrenheit = new Temperature(32.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(celsius, fahrenheit);
    }

    @Test
    @DisplayName("Should return true when 100 Celsius equals 212 Fahrenheit")
    void shouldReturnTrueWhen100CelsiusEquals212Fahrenheit() {
        Temperature celsius = new Temperature(100.0, TemperatureUnit.CELSIUS);
        Temperature fahrenheit = new Temperature(212.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(celsius, fahrenheit);
    }

    @Test
    @DisplayName("Should return true when 0 Celsius equals 273.15 Kelvin")
    void shouldReturnTrueWhen0CelsiusEquals273Point15Kelvin() {
        Temperature celsius = new Temperature(0.0, TemperatureUnit.CELSIUS);
        Temperature kelvin = new Temperature(273.15, TemperatureUnit.KELVIN);
        assertEquals(celsius, kelvin);
    }

    @Test
    @DisplayName("Should convert 100 Celsius to Fahrenheit")
    void shouldConvert100CelsiusToFahrenheit() {
        Temperature celsius = new Temperature(100.0, TemperatureUnit.CELSIUS);
        Temperature result = celsius.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 32 Fahrenheit to Celsius")
    void shouldConvert32FahrenheitToCelsius() {
        Temperature fahrenheit = new Temperature(32.0, TemperatureUnit.FAHRENHEIT);
        Temperature result = fahrenheit.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 273.15 Kelvin to Celsius")
    void shouldConvert273Point15KelvinToCelsius() {
        Temperature kelvin = new Temperature(273.15, TemperatureUnit.KELVIN);
        Temperature result = kelvin.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should return false when different temperatures")
    void shouldReturnFalseWhenDifferentTemperatures() {
        Temperature t1 = new Temperature(0.0, TemperatureUnit.CELSIUS);
        Temperature t2 = new Temperature(100.0, TemperatureUnit.CELSIUS);
        assertNotEquals(t1, t2);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void shouldReturnFalseWhenComparingWithNull() {
        Temperature t = new Temperature(0.0, TemperatureUnit.CELSIUS);
        assertNotEquals(t, null);
    }

    @Test
    @DisplayName("Should throw exception for null unit")
    void shouldThrowExceptionForNullUnit() {
        assertThrows(NullPointerException.class,
                () -> new Temperature(0.0, null));
    }

    @Test
    @DisplayName("Should have same hashCode for equal temperatures")
    void shouldHaveSameHashCodeForEqualTemperatures() {
        Temperature t1 = new Temperature(0.0, TemperatureUnit.CELSIUS);
        Temperature t2 = new Temperature(32.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    @DisplayName("Should subtract temperatures correctly")
    void shouldSubtractTemperaturesCorrectly() {
        Temperature t1 = new Temperature(100.0, TemperatureUnit.CELSIUS);
        Temperature t2 = new Temperature(40.0, TemperatureUnit.CELSIUS);
        Temperature result = t1.subtract(t2);
        assertEquals(60.0, result.getValue(), DELTA);
    }
}
