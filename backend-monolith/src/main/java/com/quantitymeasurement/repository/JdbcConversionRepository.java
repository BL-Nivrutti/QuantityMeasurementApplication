package com.quantitymeasurement.repository;

import com.quantitymeasurement.dto.ConversionResponseDto;
import com.quantitymeasurement.exception.QuantityArithmeticException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UC16 - Database Integration with JDBC
 *
 * <p>
 * JDBC-based implementation of the conversion history repository.
 * Uses parameterized SQL queries to prevent SQL injection, manages
 * connections properly, and separates persistence logic from business logic.
 * </p>
 *
 * <p>
 * Demonstrates: JDBC, connection pooling (via DataSource), parameterized
 * SQL queries, schema design, transaction management, and persistence
 * layer separation.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
public class JdbcConversionRepository {

    private final DataSource dataSource;

    private static final String INSERT_SQL = "INSERT INTO conversion_history " +
            "(input_value, input_unit, output_value, output_unit, category, success, message) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL = "SELECT id, input_value, input_unit, output_value, output_unit, " +
            "category, success, message, created_at " +
            "FROM conversion_history ORDER BY created_at DESC";

    private static final String SELECT_BY_CATEGORY_SQL = "SELECT id, input_value, input_unit, output_value, output_unit, "
            +
            "category, success, message, created_at " +
            "FROM conversion_history WHERE UPPER(category) = UPPER(?) ORDER BY created_at DESC";

    private static final String COUNT_SQL = "SELECT COUNT(*) FROM conversion_history";

    private static final String DELETE_ALL_SQL = "DELETE FROM conversion_history";

    /**
     * Constructs a JdbcConversionRepository with the given DataSource.
     *
     * @param dataSource the JDBC DataSource (must not be null)
     */
    public JdbcConversionRepository(DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "DataSource must not be null");
    }

    /**
     * Saves a conversion result to the database.
     *
     * @param response the conversion result to persist
     * @return the generated ID of the saved record
     * @throws RuntimeException if a database error occurs
     */
    public long save(ConversionResponseDto response) {
        Objects.requireNonNull(response, "Response must not be null");
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
                        Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, response.getInputValue());
            ps.setString(2, response.getInputUnit());
            ps.setDouble(3, response.getOutputValue());
            ps.setString(4, response.getOutputUnit());
            ps.setString(5, response.getCategory());
            ps.setBoolean(6, response.isSuccess());
            ps.setString(7, response.getMessage());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save conversion history", e);
        }
        return -1L;
    }

    /**
     * Returns all conversion history records.
     *
     * @return list of all conversion responses
     */
    public List<ConversionResponseDto> findAll() {
        List<ConversionResponseDto> results = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve conversion history", e);
        }
        return results;
    }

    /**
     * Returns conversion history filtered by category.
     *
     * @param category the category to filter by
     * @return list of matching conversion responses
     */
    public List<ConversionResponseDto> findByCategory(String category) {
        List<ConversionResponseDto> results = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_BY_CATEGORY_SQL)) {

            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve conversion history by category", e);
        }
        return results;
    }

    /**
     * Returns the total count of conversion records.
     *
     * @return total count
     */
    public int count() {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(COUNT_SQL);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count conversion history", e);
        }
        return 0;
    }

    // ─── Private Helpers ──────────────────────────────────────────────────────

    /**
     * Maps a ResultSet row to a ConversionResponseDto.
     *
     * @param rs the ResultSet positioned at the current row
     * @return a populated ConversionResponseDto
     * @throws SQLException if a column cannot be read
     */
    private ConversionResponseDto mapRow(ResultSet rs) throws SQLException {
        ConversionResponseDto dto = new ConversionResponseDto(
                rs.getDouble("input_value"),
                rs.getString("input_unit"),
                rs.getDouble("output_value"),
                rs.getString("output_unit"),
                rs.getString("category"));
        dto.setSuccess(rs.getBoolean("success"));
        dto.setMessage(rs.getString("message"));
        return dto;
    }
}
