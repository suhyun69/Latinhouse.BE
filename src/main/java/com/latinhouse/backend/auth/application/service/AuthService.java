package com.latinhouse.backend.auth.application.service;

import com.latinhouse.backend.auth.domain.RefreshToken;
import com.latinhouse.backend.auth.port.in.LoginUseCase;
import com.latinhouse.backend.auth.port.in.LogoutUseCase;
import com.latinhouse.backend.auth.port.in.request.EmailLoginAppRequest;
import com.latinhouse.backend.auth.port.in.response.LoginAppResponse;
import com.latinhouse.backend.auth.port.out.CreateTokenPort;
import com.latinhouse.backend.auth.port.out.DeleteTokenPort;
import com.latinhouse.backend.auth.port.out.SelectTokenPort;
import com.latinhouse.backend.auth.port.out.TokenPort;
import com.latinhouse.backend.global.exception.CustomException;
import com.latinhouse.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase, LogoutUseCase {

    private final AuthenticationManager authenticationManager;
    private final TokenPort tokenPort;
    private final CreateTokenPort createTokenPort;
    private final SelectTokenPort selectTokenPort;
    private final DeleteTokenPort deleteTokenPort;

    @Override
    public LoginAppResponse emailLogin(EmailLoginAppRequest appReq) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appReq.getEmail(), appReq.getPassword()));

        String token = tokenPort.generateToken(appReq.getEmail());
        String refreshToken = tokenPort.generateRefreshToken(appReq.getEmail());

        createTokenPort.createRefreshToken(appReq.getEmail(), refreshToken);

        return LoginAppResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginAppResponse refresh(String refreshToken) {

        if (refreshToken == null || !tokenPort.validateToken(refreshToken)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "Refresh token invalid");
        }

        String email = tokenPort.extractUsername(refreshToken);
        RefreshToken saved = selectTokenPort.findRefreshTokenByEmail(email).orElse(null);
        if (saved == null || !saved.getRefreshToken().equals(refreshToken)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "Refresh token mismatch");
        }

        String newToken = tokenPort.generateToken(email);

        return LoginAppResponse.builder()
                .token(newToken)
                .build();
    }

    @Override
    public void logout(String email) {
        deleteTokenPort.deleteRefreshToken(email);
    }
}
