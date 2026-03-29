package com.latinhouse.backend.auth.adapter.in.web.response;

import com.latinhouse.backend.user.port.in.response.UserAppResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailSignupWebResponse {
    private String email;

    public EmailSignupWebResponse(UserAppResponse appRes) {
        this.email = appRes.getEmail();
    }
}
