package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.ConversionRequestDto;
import com.quantitymeasurement.dto.ConversionResponseDto;
import com.quantitymeasurement.entity.ConversionHistory;
import com.quantitymeasurement.repository.ConversionHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UC17 - Spring Boot Backend: Spring Service Layer
 *
 * <p>
 * Spring-managed service that wraps the domain {@link ConversionService}
 * and adds persistence, logging, and transaction management.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Service
public class QuantityConversionService {

    private static final Logger log = LoggerFactory.getLogger(QuantityConversionService.class);

    private final ConversionHistoryRepository conversionHistoryRepository;
    private final ConversionService conversionService;

    public QuantityConversionService(ConversionHistoryRepository conversionHistoryRepository,
            ConversionService conversionService) {
        this.conversionHistoryRepository = conversionHistoryRepository;
        this.conversionService = conversionService;
    }

    /**
     * Performs a unit conversion and persists the result to the database.
     *
     * @param request the conversion request DTO
     * @return the conversion response DTO
     */
    @Transactional
    public ConversionResponseDto convert(ConversionRequestDto request) {
        log.info("Processing conversion request: {}", request);

        ConversionResponseDto response = conversionService.convert(request);

        ConversionHistory history = ConversionHistory.builder()
                .inputValue(request.getValue())
                .inputUnit(request.getFromUnit())
                .outputValue(response.getOutputValue())
                .outputUnit(request.getToUnit())
                .category(request.getCategory().toUpperCase())
                .success(response.isSuccess())
                .message(response.getMessage())
                .build();

        conversionHistoryRepository.save(history);
        log.info("Conversion saved: {} {} -> {} {}",
                request.getValue(), request.getFromUnit(),
                response.getOutputValue(), request.getToUnit());

        return response;
    }

    /**
     * Returns all conversion history.
     *
     * @return list of all conversion history records
     */
    @Transactional(readOnly = true)
    public List<ConversionHistory> getHistory() {
        return conversionHistoryRepository.findAll();
    }

    /**
     * Returns conversion history filtered by category.
     *
     * @param category the measurement category
     * @return list of matching history records
     */
    @Transactional(readOnly = true)
    public List<ConversionHistory> getHistoryByCategory(String category) {
        return conversionHistoryRepository.findByCategoryIgnoreCaseOrderByCreatedAtDesc(category);
    }

    /**
     * Returns the most recent N conversion history records.
     *
     * @param limit the maximum number of records
     * @return list of recent records
     */
    @Transactional(readOnly = true)
    public List<ConversionHistory> getRecentHistory(int limit) {
        return conversionHistoryRepository.findRecentHistory(limit);
    }
}
