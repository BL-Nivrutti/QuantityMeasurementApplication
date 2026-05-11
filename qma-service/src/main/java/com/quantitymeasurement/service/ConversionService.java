package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.ConversionRequestDto;
import com.quantitymeasurement.dto.ConversionResponseDto;
import com.quantitymeasurement.enums.*;
import com.quantitymeasurement.exception.InvalidUnitException;
import com.quantitymeasurement.exception.UnsupportedCategoryException;
import com.quantitymeasurement.model.GenericQuantity;
import com.quantitymeasurement.model.Temperature;

import java.util.Objects;

/**
 * UC15 - N-Tier Architecture: Service Layer
 *
 * <p>
 * Central service that handles all quantity conversion requests.
 * Accepts {@link ConversionRequestDto}, delegates to the appropriate
 * domain logic, and returns a {@link ConversionResponseDto}.
 * </p>
 *
 * <p>
 * Demonstrates: service layer, DTO usage, dependency injection readiness,
 * SOLID principles, and clean separation of concerns.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class ConversionService {

    /**
     * Performs a unit conversion based on the provided request DTO.
     *
     * @param request the conversion request (must not be null)
     * @return a response DTO with the conversion result
     * @throws NullPointerException         if request is null
     * @throws InvalidUnitException         if the unit is not recognized
     * @throws UnsupportedCategoryException if the category is not supported
     */
    public ConversionResponseDto convert(ConversionRequestDto request) {
        Objects.requireNonNull(request, "Conversion request must not be null");

        String category = request.getCategory().toUpperCase();

        return switch (category) {
            case "LENGTH" -> convertLength(request);
            case "WEIGHT" -> convertWeight(request);
            case "VOLUME" -> convertVolume(request);
            case "TEMPERATURE" -> convertTemperature(request);
            default -> throw new UnsupportedCategoryException(
                    "Unsupported category: " + category);
        };
    }

    // ─── Private Conversion Methods ───────────────────────────────────────────

    private ConversionResponseDto convertLength(ConversionRequestDto req) {
        LengthUnit from = parseLengthUnit(req.getFromUnit());
        LengthUnit to = parseLengthUnit(req.getToUnit());
        GenericQuantity<LengthUnit> quantity = new GenericQuantity<>(req.getValue(), from);
        GenericQuantity<LengthUnit> result = quantity.convertTo(to);
        return new ConversionResponseDto(req.getValue(), req.getFromUnit(),
                result.getValue(), req.getToUnit(), "LENGTH");
    }

    private ConversionResponseDto convertWeight(ConversionRequestDto req) {
        WeightUnit from = parseWeightUnit(req.getFromUnit());
        WeightUnit to = parseWeightUnit(req.getToUnit());
        GenericQuantity<WeightUnit> quantity = new GenericQuantity<>(req.getValue(), from);
        GenericQuantity<WeightUnit> result = quantity.convertTo(to);
        return new ConversionResponseDto(req.getValue(), req.getFromUnit(),
                result.getValue(), req.getToUnit(), "WEIGHT");
    }

    private ConversionResponseDto convertVolume(ConversionRequestDto req) {
        VolumeUnit from = parseVolumeUnit(req.getFromUnit());
        VolumeUnit to = parseVolumeUnit(req.getToUnit());
        GenericQuantity<VolumeUnit> quantity = new GenericQuantity<>(req.getValue(), from);
        GenericQuantity<VolumeUnit> result = quantity.convertTo(to);
        return new ConversionResponseDto(req.getValue(), req.getFromUnit(),
                result.getValue(), req.getToUnit(), "VOLUME");
    }

    private ConversionResponseDto convertTemperature(ConversionRequestDto req) {
        TemperatureUnit from = parseTemperatureUnit(req.getFromUnit());
        TemperatureUnit to = parseTemperatureUnit(req.getToUnit());
        Temperature temperature = new Temperature(req.getValue(), from);
        Temperature result = temperature.convertTo(to);
        return new ConversionResponseDto(req.getValue(), req.getFromUnit(),
                result.getValue(), req.getToUnit(), "TEMPERATURE");
    }

    // ─── Unit Parsers ─────────────────────────────────────────────────────────

    private LengthUnit parseLengthUnit(String unit) {
        try {
            return LengthUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid length unit: " + unit);
        }
    }

    private WeightUnit parseWeightUnit(String unit) {
        try {
            return WeightUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid weight unit: " + unit);
        }
    }

    private VolumeUnit parseVolumeUnit(String unit) {
        try {
            return VolumeUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid volume unit: " + unit);
        }
    }

    private TemperatureUnit parseTemperatureUnit(String unit) {
        try {
            return TemperatureUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid temperature unit: " + unit);
        }
    }
}
