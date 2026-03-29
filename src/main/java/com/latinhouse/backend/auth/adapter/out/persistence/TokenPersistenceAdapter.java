package com.latinhouse.backend.auth.adapter.out.persistence;

import com.latinhouse.backend.auth.adapter.out.persistence.entity.RefreshTokenJpaEntity;
import com.latinhouse.backend.auth.adapter.out.persistence.mapper.RefreshTokenMapper;
import com.latinhouse.backend.auth.adapter.out.persistence.repository.RefreshTokenRepository;
import com.latinhouse.backend.auth.domain.RefreshToken;
import com.latinhouse.backend.auth.port.out.CreateTokenPort;
import com.latinhouse.backend.auth.port.out.DeleteTokenPort;
import com.latinhouse.backend.auth.port.out.SelectTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements CreateTokenPort, SelectTokenPort, DeleteTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public void createRefreshToken(String email, String refreshToken) {

        RefreshTokenJpaEntity refreshTokenT = refreshTokenMapper.mapToJpaEntity(email, refreshToken);
        refreshTokenRepository.save(refreshTokenT);
    }

    @Override
    public Optional<RefreshToken> findRefreshTokenByEmail(String email) {

        return refreshTokenRepository.findById(email)
                .map(refreshTokenMapper::mapToDomainEntity);
    }

    @Override
    public void deleteRefreshToken(String email) {
        refreshTokenRepository.deleteById(email);
    }
}
