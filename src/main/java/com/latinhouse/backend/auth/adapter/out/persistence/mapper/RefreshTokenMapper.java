package com.latinhouse.backend.auth.adapter.out.persistence.mapper;

import com.latinhouse.backend.auth.adapter.out.persistence.entity.RefreshTokenJpaEntity;
import com.latinhouse.backend.auth.domain.RefreshToken;
import com.latinhouse.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RefreshTokenMapper {

    public RefreshTokenJpaEntity mapToJpaEntity(String email, String refreshToken) {
        return RefreshTokenJpaEntity.builder()
                .username(email)
                .refreshToken(refreshToken)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();
    }

    public RefreshToken mapToDomainEntity(RefreshTokenJpaEntity refreshTokenT) {
        return RefreshToken.builder()
                .username(refreshTokenT.getUsername())
                .refreshToken(refreshTokenT.getRefreshToken())
                .expiryDate(refreshTokenT.getExpiryDate())
                .build();
    }
}
