-- ============================================================
-- Quantity Measurement Application - Database Schema
-- UC16 - Database Integration with JDBC
-- Author: Nivrutti
-- ============================================================

CREATE DATABASE IF NOT EXISTS quantity_measurement_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE quantity_measurement_db;

-- ─── Conversion History Table ─────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS conversion_history (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    input_value   DOUBLE         NOT NULL,
    input_unit    VARCHAR(50)    NOT NULL,
    output_value  DOUBLE         NOT NULL,
    output_unit   VARCHAR(50)    NOT NULL,
    category      VARCHAR(50)    NOT NULL,
    success       BOOLEAN        NOT NULL DEFAULT TRUE,
    message       VARCHAR(255),
    created_at    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category (category),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB;

-- ─── Users Table (for UC17/UC18) ──────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS users (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(100)   NOT NULL UNIQUE,
    email         VARCHAR(255)   NOT NULL UNIQUE,
    password_hash VARCHAR(255),
    provider      VARCHAR(50)    NOT NULL DEFAULT 'LOCAL',  -- LOCAL, GOOGLE
    provider_id   VARCHAR(255),
    role          VARCHAR(50)    NOT NULL DEFAULT 'USER',
    enabled       BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_provider (provider)
) ENGINE=InnoDB;

-- ─── Sample Data ──────────────────────────────────────────────────────────────
INSERT INTO conversion_history (input_value, input_unit, output_value, output_unit, category, message)
VALUES
    (1.0,   'FEET',      12.0,    'INCH',        'LENGTH',      'Conversion successful'),
    (1.0,   'KILOGRAM',  1000.0,  'GRAM',        'WEIGHT',      'Conversion successful'),
    (1.0,   'LITRE',     1000.0,  'MILLILITRE',  'VOLUME',      'Conversion successful'),
    (0.0,   'CELSIUS',   32.0,    'FAHRENHEIT',  'TEMPERATURE', 'Conversion successful');
