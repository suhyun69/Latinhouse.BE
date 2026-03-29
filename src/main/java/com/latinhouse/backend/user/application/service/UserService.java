package com.latinhouse.backend.user.application.service;

import com.latinhouse.backend.global.exception.UserNotFoundException;
import com.latinhouse.backend.user.domain.User;
import com.latinhouse.backend.user.port.in.FindUserUseCase;
import com.latinhouse.backend.user.port.in.SignupUseCase;
import com.latinhouse.backend.user.port.in.UpdateUserUseCase;
import com.latinhouse.backend.user.port.in.request.EmailSignupAppRequest;
import com.latinhouse.backend.user.port.in.request.UpdateUserAppRequest;
import com.latinhouse.backend.user.port.in.response.UserAppResponse;
import com.latinhouse.backend.user.port.out.CreateUserPort;
import com.latinhouse.backend.user.port.out.ReadUserPort;
import com.latinhouse.backend.user.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements SignupUseCase
        , FindUserUseCase
        , UpdateUserUseCase
{

    private final CreateUserPort createUserPort;
    private final ReadUserPort readUserPort;
    private final UpdateUserPort updateUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAppResponse emailSignup(EmailSignupAppRequest appReq) {
        User user = User.builder()
                .email(appReq.getEmail())
                .password(passwordEncoder.encode(appReq.getPassword()))
                .build();
        return new UserAppResponse(createUserPort.create(user));
    }

    @Override
    public UserAppResponse findByEmail(String id) {
        return readUserPort.findByEmail(id)
                .map(UserAppResponse::new)
                .orElse(null);
    }

    @Override
    public List<UserAppResponse> findAll() {
        return readUserPort.findAll().stream()
                .map(UserAppResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserAppResponse update(String id, UpdateUserAppRequest appReq) {
        User user = readUserPort.findByEmail(id)
                .orElseThrow(UserNotFoundException::new);
        user.setPassword(appReq.getPassword());

        User updated = updateUserPort.update(user);
        return new UserAppResponse(updated);
    }
}
