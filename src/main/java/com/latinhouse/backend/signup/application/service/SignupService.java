package com.latinhouse.backend.signup.application.service;

import com.latinhouse.backend.signup.port.in.SignupUseCase;
import com.latinhouse.backend.signup.port.in.request.EmailSignupAppRequest;
import com.latinhouse.backend.signup.port.in.response.SignupAppResponse;
import com.latinhouse.backend.signup.port.out.RegisterUserPort;
import com.latinhouse.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService implements SignupUseCase {

    private final RegisterUserPort registerUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupAppResponse emailSignup(EmailSignupAppRequest appReq) {
        User user = User.builder()
                .email(appReq.getEmail())
                .password(passwordEncoder.encode(appReq.getPassword()))
                .build();
        return new SignupAppResponse(registerUserPort.register(user));
    }
}
