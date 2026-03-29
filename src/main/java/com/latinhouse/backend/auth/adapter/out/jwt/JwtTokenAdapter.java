package com.latinhouse.backend.auth.adapter.out.jwt;

import com.latinhouse.backend.auth.port.out.TokenPort;
import com.latinhouse.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenAdapter implements TokenPort {

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(String email) {
        return jwtUtil.generateToken(email);
    }

    @Override
    public String generateRefreshToken(String email) {
        return jwtUtil.generateRefreshToken(email);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    @Override
    public String extractUsername(String token) {
        return jwtUtil.extractUsername(token);
    }
}
