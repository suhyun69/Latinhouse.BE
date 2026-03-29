package com.latinhouse.backend.auth.adapter.out.persistence.repository;

import com.latinhouse.backend.auth.adapter.out.persistence.entity.RefreshTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenJpaEntity, String> {}
