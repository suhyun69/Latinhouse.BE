package com.latinhouse.backend.auth.adapter.in.web;

import com.latinhouse.backend.auth.adapter.in.web.request.EmailLoginWebRequest;
import com.latinhouse.backend.auth.adapter.in.web.request.EmailSignupWebRequest;
import com.latinhouse.backend.auth.adapter.in.web.response.EmailSignupWebResponse;
import com.latinhouse.backend.auth.adapter.in.web.response.LoginWebResponse;
import com.latinhouse.backend.auth.port.in.LoginUseCase;
import com.latinhouse.backend.auth.port.in.LogoutUseCase;
import com.latinhouse.backend.auth.port.in.request.EmailLoginAppRequest;
import com.latinhouse.backend.auth.port.in.response.LoginAppResponse;
import com.latinhouse.backend.user.port.in.SignupUseCase;
import com.latinhouse.backend.user.port.in.request.EmailSignupAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "auth", description = "auth API Document")
@RequiredArgsConstructor
public class ApiV1AuthController {

    private final SignupUseCase signupUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup/email")
    @Operation(summary = "Signup", description = "by email")
    public ResponseEntity<EmailSignupWebResponse> emailSignup(@Valid @RequestBody EmailSignupWebRequest webReq) {

        EmailSignupAppRequest appReq = EmailSignupAppRequest.builder()
                .email(webReq.getEmail())
                .password(passwordEncoder.encode(webReq.getPassword()))
                .build();
        EmailSignupWebResponse response = new EmailSignupWebResponse(signupUseCase.emailSignup(appReq));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login/email")
    @Operation(summary = "Login", description = "by email")
    public ResponseEntity<LoginWebResponse> emailLogin(@Valid @RequestBody EmailLoginWebRequest webReq, HttpServletResponse response) {
        EmailLoginAppRequest appReq = EmailLoginAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();

        LoginAppResponse appRes = loginUseCase.emailLogin(appReq);

        // accessToken은 쿠키로
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", appRes.getToken())
                .httpOnly(true).secure(true).path("/").maxAge(15 * 60).build();
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());

        // refreshToken도 쿠키로
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", appRes.getRefreshToken())
                .httpOnly(true).secure(true).path("/").maxAge(7 * 24 * 60 * 60).build();
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        LoginWebResponse webRes = new LoginWebResponse(appRes);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(webRes);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {

        LoginAppResponse appRes = loginUseCase.refresh(request);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", appRes.getRefreshToken())
                .httpOnly(true).secure(true).path("/").maxAge(15 * 60).build();
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());

        LoginWebResponse webRes = new LoginWebResponse(appRes);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(webRes);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

        logoutUseCase.logout(request);

        // 쿠키 삭제
        ResponseCookie deleteAccess = ResponseCookie.from("accessToken", "")
                .httpOnly(true).secure(true).path("/").maxAge(0).build();
        ResponseCookie deleteRefresh = ResponseCookie.from("refreshToken", "")
                .httpOnly(true).secure(true).path("/").maxAge(0).build();

        response.addHeader(HttpHeaders.SET_COOKIE, deleteAccess.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, deleteRefresh.toString());

        return ResponseEntity.ok("로그아웃 완료");
    }
}
