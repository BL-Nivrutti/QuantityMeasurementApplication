package com.quantitymeasurement.enums;

/**
 * UC8 - Refactor Unit Enum to Standalone Architecture
 *
 * <p>
 * A standalone interface that all unit enums must implement. Applying
 * SRP and separation of concerns, each unit category (length, weight,
 * volume) has its own enum that implements this interface. The conversion
 * logic is fully encapsulated within each enum, following the delegation
 * pattern and enabling architectural scalability.
 * </p>
 *
 * <p>
 * Demonstrates: SRP, separation of concerns, delegation pattern,
 * encapsulation of conversion logic, enum capabilities, and
 * architectural scalability.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public interface Unit {

    /**
     * Returns the conversion factor to the base unit for this category.
     *
     * @return conversion factor (base unit = 1.0)
     */
    double getConversionFactor();

    /**
     * Returns the name of this unit.
     *
     * @return unit name
     */
    String name();
}
