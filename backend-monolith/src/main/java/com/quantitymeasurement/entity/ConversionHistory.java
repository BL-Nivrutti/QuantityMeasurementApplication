package com.quantitymeasurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * UC17 - Spring Boot Backend: JPA Entity
 *
 * <p>
 * JPA entity representing a conversion history record in the database.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Entity
@Table(name = "conversion_history", indexes = {
        @Index(name = "idx_category", columnList = "category"),
        @Index(name = "idx_created_at", columnList = "createdAt")
})
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_value", nullable = false)
    private Double inputValue;

    @Column(name = "input_unit", nullable = false, length = 50)
    private String inputUnit;

    @Column(name = "output_value", nullable = false)
    private Double outputValue;

    @Column(name = "output_unit", nullable = false, length = 50)
    private String outputUnit;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "success", nullable = false)
    private Boolean success;

    @Column(name = "message", length = 255)
    private String message;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public ConversionHistory() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ─── Builder ──────────────────────────────────────────────────────────────

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Double inputValue;
        private String inputUnit;
        private Double outputValue;
        private String outputUnit;
        private String category;
        private Boolean success;
        private String message;

        public Builder inputValue(Double v) {
            this.inputValue = v;
            return this;
        }

        public Builder inputUnit(String v) {
            this.inputUnit = v;
            return this;
        }

        public Builder outputValue(Double v) {
            this.outputValue = v;
            return this;
        }

        public Builder outputUnit(String v) {
            this.outputUnit = v;
            return this;
        }

        public Builder category(String v) {
            this.category = v;
            return this;
        }

        public Builder success(Boolean v) {
            this.success = v;
            return this;
        }

        public Builder message(String v) {
            this.message = v;
            return this;
        }

        public ConversionHistory build() {
            ConversionHistory h = new ConversionHistory();
            h.inputValue = this.inputValue;
            h.inputUnit = this.inputUnit;
            h.outputValue = this.outputValue;
            h.outputUnit = this.outputUnit;
            h.category = this.category;
            h.success = this.success;
            h.message = this.message;
            return h;
        }
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getInputValue() {
        return inputValue;
    }

    public void setInputValue(Double inputValue) {
        this.inputValue = inputValue;
    }

    public String getInputUnit() {
        return inputUnit;
    }

    public void setInputUnit(String inputUnit) {
        this.inputUnit = inputUnit;
    }

    public Double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(Double outputValue) {
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ConversionHistory))
            return false;
        ConversionHistory that = (ConversionHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ConversionHistory{id=" + id + ", category='" + category + "'}";
    }
}
