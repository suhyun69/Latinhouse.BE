package com.latinhouse.backend.auth.port.out;

public interface DeleteTokenPort {
    void deleteRefreshToken(String email);
}
