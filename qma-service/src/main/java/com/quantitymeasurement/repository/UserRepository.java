package com.quantitymeasurement.repository;

import com.quantitymeasurement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UC17 - Spring Boot Backend: User Repository
 *
 * @author Nivrutti
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
