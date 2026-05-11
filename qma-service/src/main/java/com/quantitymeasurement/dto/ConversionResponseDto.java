package com.quantitymeasurement.dto;

/**
 * UC15 - N-Tier Architecture: DTO Layer
 *
 * <p>
 * Data Transfer Object for a quantity conversion response.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class ConversionResponseDto {

    private double inputValue;
    private String inputUnit;
    private double outputValue;
    private String outputUnit;
    private String category;
    private boolean success;
    private String message;

    public ConversionResponseDto() {
    }

    public ConversionResponseDto(double inputValue, String inputUnit,
            double outputValue, String outputUnit,
            String category) {
        this.inputValue = inputValue;
        this.inputUnit = inputUnit;
        this.outputValue = outputValue;
        this.outputUnit = outputUnit;
        this.category = category;
        this.success = true;
        this.message = "Conversion successful";
    }

    public static ConversionResponseDto error(String message) {
        ConversionResponseDto dto = new ConversionResponseDto();
        dto.success = false;
        dto.message = message;
        return dto;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public String getInputUnit() {
        return inputUnit;
    }

    public void setInputUnit(String inputUnit) {
        this.inputUnit = inputUnit;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public String getOutputUnit() {
        return outputUnit;
    }

    public void setOutputUnit(String outputUnit) {
        this.outputUnit = outputUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
