package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.ConversionRequestDto;
import com.quantitymeasurement.dto.ConversionResponseDto;
import com.quantitymeasurement.exception.InvalidUnitException;
import com.quantitymeasurement.exception.UnsupportedCategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC15 - N-Tier Architecture: ConversionService Tests
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@DisplayName("UC15 - N-Tier Architecture: ConversionService")
class ConversionServiceTest {

    private static final double DELTA = 1e-3;
    private ConversionService service;

    @BeforeEach
    void setUp() {
        service = new ConversionService();
    }

    @Test
    @DisplayName("Should convert 1 foot to 12 inches via DTO")
    void shouldConvert1FootTo12InchesViaDto() {
        ConversionRequestDto request = new ConversionRequestDto(1.0, "FEET", "INCH", "LENGTH");
        ConversionResponseDto response = service.convert(request);
        assertTrue(response.isSuccess());
        assertEquals(12.0, response.getOutputValue(), DELTA);
        assertEquals("INCH", response.getOutputUnit());
    }

    @Test
    @DisplayName("Should convert 1 kg to 1000 grams via DTO")
    void shouldConvert1KgTo1000GramsViaDto() {
        ConversionRequestDto request = new ConversionRequestDto(1.0, "KILOGRAM", "GRAM", "WEIGHT");
        ConversionResponseDto response = service.convert(request);
        assertTrue(response.isSuccess());
        assertEquals(1000.0, response.getOutputValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 1 litre to 1000 ml via DTO")
    void shouldConvert1LitreTo1000MlViaDto() {
        ConversionRequestDto request = new ConversionRequestDto(1.0, "LITRE", "MILLILITRE", "VOLUME");
        ConversionResponseDto response = service.convert(request);
        assertTrue(response.isSuccess());
        assertEquals(1000.0, response.getOutputValue(), DELTA);
    }

    @Test
    @DisplayName("Should convert 0 Celsius to 32 Fahrenheit via DTO")
    void shouldConvert0CelsiusTo32FahrenheitViaDto() {
        ConversionRequestDto request = new ConversionRequestDto(0.0, "CELSIUS", "FAHRENHEIT", "TEMPERATURE");
        ConversionResponseDto response = service.convert(request);
        assertTrue(response.isSuccess());
        assertEquals(32.0, response.getOutputValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw exception for invalid unit")
    void shouldThrowExceptionForInvalidUnit() {
        ConversionRequestDto request = new ConversionRequestDto(1.0, "INVALID", "INCH", "LENGTH");
        assertThrows(InvalidUnitException.class, () -> service.convert(request));
    }

    @Test
    @DisplayName("Should throw exception for unsupported category")
    void shouldThrowExceptionForUnsupportedCategory() {
        ConversionRequestDto request = new ConversionRequestDto(1.0, "FEET", "INCH", "ENERGY");
        assertThrows(UnsupportedCategoryException.class, () -> service.convert(request));
    }

    @Test
    @DisplayName("Should throw exception for null request")
    void shouldThrowExceptionForNullRequest() {
        assertThrows(NullPointerException.class, () -> service.convert(null));
    }

    @Test
    @DisplayName("Should handle case-insensitive category")
    void shouldHandleCaseInsensitiveCategory() {
        ConversionRequestDto request = new ConversionRequestDto(1.0, "FEET", "INCH", "length");
        ConversionResponseDto response = service.convert(request);
        assertTrue(response.isSuccess());
    }
}
