package com.latinhouse.backend.signup.adapter.in.web;

import com.latinhouse.backend.signup.adapter.in.web.request.EmailSignupWebRequest;
import com.latinhouse.backend.signup.adapter.in.web.response.EmailSignupWebResponse;
import com.latinhouse.backend.signup.port.in.SignupUseCase;
import com.latinhouse.backend.signup.port.in.request.EmailSignupAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/signup")
@Tag(name = "signup", description = "signup API Document")
@RequiredArgsConstructor
public class ApiV1SignupController {

    private final SignupUseCase signupUseCase;

    @PostMapping("/email")
    @Operation(summary = "Signup", description = "by email")
    public ResponseEntity<EmailSignupWebResponse> emailSignup(@Valid @RequestBody EmailSignupWebRequest webReq) {

        EmailSignupAppRequest appReq = EmailSignupAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EmailSignupWebResponse(signupUseCase.emailSignup(appReq)));
    }
}
