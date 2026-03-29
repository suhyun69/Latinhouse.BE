package com.latinhouse.backend.auth.port.out;

import com.latinhouse.backend.auth.domain.RefreshToken;

import java.util.Optional;

public interface SelectTokenPort {
    Optional<RefreshToken> findRefreshTokenByEmail(String email);
}

