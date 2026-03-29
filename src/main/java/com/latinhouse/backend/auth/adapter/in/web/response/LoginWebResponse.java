package com.latinhouse.backend.auth.adapter.in.web.response;

import com.latinhouse.backend.auth.port.in.response.LoginAppResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginWebResponse {
    private String token;
    private String refreshToken;

    public LoginWebResponse(LoginAppResponse appRes) {
        this.token = appRes.getToken();
        this.refreshToken = appRes.getRefreshToken();
    }
}
