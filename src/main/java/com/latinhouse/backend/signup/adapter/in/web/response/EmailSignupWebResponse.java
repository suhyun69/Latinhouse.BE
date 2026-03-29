package com.latinhouse.backend.signup.adapter.in.web.response;

import com.latinhouse.backend.signup.port.in.response.SignupAppResponse;
import lombok.Getter;

@Getter
public class EmailSignupWebResponse {
    private final String email;

    public EmailSignupWebResponse(SignupAppResponse appRes) {
        this.email = appRes.getEmail();
    }
}
