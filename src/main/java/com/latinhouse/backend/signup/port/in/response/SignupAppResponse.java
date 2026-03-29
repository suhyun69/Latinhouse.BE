package com.latinhouse.backend.signup.port.in.response;

import lombok.Getter;

@Getter
public class SignupAppResponse {
    private final String email;

    public SignupAppResponse(String email) {
        this.email = email;
    }
}
