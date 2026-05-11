package com.quantitymeasurement.repository;

import com.quantitymeasurement.dto.ConversionResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UC15 - N-Tier Architecture: Repository/DAO Layer
 *
 * <p>
 * In-memory repository for storing conversion history. This will be
 * replaced by a JDBC/JPA implementation in UC16/UC17.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class ConversionRepository {

    /** In-memory store for conversion history */
    private final List<ConversionResponseDto> history = new ArrayList<>();

    /**
     * Saves a conversion result to the history.
     *
     * @param response the conversion result to save
     */
    public void save(ConversionResponseDto response) {
        if (response != null) {
            history.add(response);
        }
    }

    /**
     * Returns all conversion history entries.
     *
     * @return unmodifiable list of all conversions
     */
    public List<ConversionResponseDto> findAll() {
        return Collections.unmodifiableList(history);
    }

    /**
     * Returns conversion history filtered by category.
     *
     * @param category the category to filter by (e.g., "LENGTH")
     * @return list of conversions for the given category
     */
    public List<ConversionResponseDto> findByCategory(String category) {
        return history.stream()
                .filter(r -> category.equalsIgnoreCase(r.getCategory()))
                .toList();
    }

    /**
     * Clears all conversion history.
     */
    public void clear() {
        history.clear();
    }

    /**
     * Returns the total number of conversions stored.
     *
     * @return count of stored conversions
     */
    public int count() {
        return history.size();
    }
}
