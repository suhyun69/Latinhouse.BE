package com.latinhouse.backend.user.port.in;

import com.latinhouse.backend.user.port.in.response.UserAppResponse;

import java.util.List;

public interface FindUserUseCase {
    UserAppResponse findByEmail(String email);
    List<UserAppResponse> findAll();
}
