package com.latinhouse.backend.auth.adapter.in.web;

import com.latinhouse.backend.auth.adapter.in.web.request.EmailLoginWebRequest;
import com.latinhouse.backend.auth.adapter.in.web.response.LoginWebResponse;
import com.latinhouse.backend.auth.port.in.LoginUseCase;
import com.latinhouse.backend.auth.port.in.LogoutUseCase;
import com.latinhouse.backend.auth.port.in.request.EmailLoginAppRequest;
import com.latinhouse.backend.auth.port.in.response.LoginAppResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "auth", description = "auth API Document")
@RequiredArgsConstructor
public class ApiV1AuthController {

    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;

    @PostMapping("/login/email")
    @Operation(summary = "Login", description = "by email")
    public ResponseEntity<LoginWebResponse> emailLogin(@Valid @RequestBody EmailLoginWebRequest webReq) {
        EmailLoginAppRequest appReq = EmailLoginAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();
        return ResponseEntity.ok(new LoginWebResponse(loginUseCase.emailLogin(appReq)));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<LoginWebResponse> refreshToken(HttpServletRequest request) {
        String refreshToken = extractBearerToken(request);
        return ResponseEntity.ok(new LoginWebResponse(loginUseCase.refresh(refreshToken)));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        logoutUseCase.logout(email);
        return ResponseEntity.ok("로그아웃 완료");
    }

    private String extractBearerToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
