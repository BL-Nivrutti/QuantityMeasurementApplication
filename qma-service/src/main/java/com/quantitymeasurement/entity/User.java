package com.quantitymeasurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * UC17 - Spring Boot Backend: User JPA Entity
 *
 * <p>
 * JPA entity representing an application user. Supports both local
 * (username/password) and OAuth2 (Google) authentication.
 * </p>
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_provider", columnList = "provider")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "provider", nullable = false, length = 50)
    private String provider = "LOCAL";

    @Column(name = "provider_id", length = 255)
    private String providerId;

    @Column(name = "role", nullable = false, length = 50)
    private String role = "USER";

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ─── Builder ──────────────────────────────────────────────────────────────

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String email;
        private String passwordHash;
        private String provider = "LOCAL";
        private String providerId;
        private String role = "USER";
        private Boolean enabled = true;

        public Builder username(String v) {
            this.username = v;
            return this;
        }

        public Builder email(String v) {
            this.email = v;
            return this;
        }

        public Builder passwordHash(String v) {
            this.passwordHash = v;
            return this;
        }

        public Builder provider(String v) {
            this.provider = v;
            return this;
        }

        public Builder providerId(String v) {
            this.providerId = v;
            return this;
        }

        public Builder role(String v) {
            this.role = v;
            return this;
        }

        public Builder enabled(Boolean v) {
            this.enabled = v;
            return this;
        }

        public User build() {
            User u = new User();
            u.username = this.username;
            u.email = this.email;
            u.passwordHash = this.passwordHash;
            u.provider = this.provider;
            u.providerId = this.providerId;
            u.role = this.role;
            u.enabled = this.enabled;
            return u;
        }
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User that = (User) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", email='" + email + "', role='" + role + "'}";
    }
}
