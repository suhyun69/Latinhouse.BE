package com.latinhouse.backend.auth.port.out;

public interface TokenPort {
    String generateToken(String email);
    String generateRefreshToken(String email);
    boolean validateToken(String token);
    String extractUsername(String token);
}
