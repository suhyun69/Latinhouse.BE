package com.latinhouse.backend.signup.port.in.response;

import com.latinhouse.backend.user.domain.User;
import lombok.Getter;

@Getter
public class SignupAppResponse {
    private final String email;

    public SignupAppResponse(User user) {
        this.email = user.getEmail();
    }
}
