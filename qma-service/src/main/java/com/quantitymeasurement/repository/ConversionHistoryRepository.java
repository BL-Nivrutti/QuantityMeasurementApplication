package com.quantitymeasurement.repository;

import com.quantitymeasurement.entity.ConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UC17 - Spring Boot Backend: Spring Data JPA Repository
 *
 * <p>
 * Spring Data JPA repository for {@link ConversionHistory} entities.
 * Provides CRUD operations and custom query methods.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

    /**
     * Finds all conversion history records for a given category.
     *
     * @param category the measurement category (e.g., "LENGTH")
     * @return list of matching records ordered by creation time descending
     */
    List<ConversionHistory> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category);

    /**
     * Finds the most recent N conversion history records.
     *
     * @param limit the maximum number of records to return
     * @return list of recent records
     */
    @Query(value = "SELECT * FROM conversion_history ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
    List<ConversionHistory> findRecentHistory(@Param("limit") int limit);

    /**
     * Counts conversions by category.
     *
     * @param category the category to count
     * @return count of conversions for the category
     */
    long countByCategoryIgnoreCase(String category);
}
