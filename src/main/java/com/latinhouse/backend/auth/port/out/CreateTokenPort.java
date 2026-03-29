package com.latinhouse.backend.auth.port.out;

public interface CreateTokenPort {
    void createRefreshToken(String email, String refreshToken);
}
