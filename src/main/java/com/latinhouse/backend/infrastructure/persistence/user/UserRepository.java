package com.latinhouse.backend.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
    Optional<UserJpaEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
