package com.quantitymeasurement.dto;

/**
 * UC15 - N-Tier Architecture: DTO Layer
 *
 * <p>
 * Data Transfer Object for a quantity conversion request.
 * Decouples the presentation layer from the domain model.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class ConversionRequestDto {

    private double value;
    private String fromUnit;
    private String toUnit;
    private String category; // LENGTH, WEIGHT, VOLUME, TEMPERATURE

    public ConversionRequestDto() {
    }

    public ConversionRequestDto(double value, String fromUnit, String toUnit, String category) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ConversionRequestDto{value=" + value + ", fromUnit='" + fromUnit
                + "', toUnit='" + toUnit + "', category='" + category + "'}";
    }
}
