package com.latinhouse.backend.user.application.service;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public UserAppResponse emailSignup(EmailSignupAppRequest appReq) {
        return new UserAppResponse(createUserPort.create(appReq));
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
        Optional<User> optionalUser = readUserPort.findByEmail(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        user.setPassword(appReq.getPassword());

        User updated = updateUserPort.update(user);
        return new UserAppResponse(updated);
    }
}
