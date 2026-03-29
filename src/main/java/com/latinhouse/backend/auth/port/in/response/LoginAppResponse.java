package com.latinhouse.backend.auth.port.in.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginAppResponse {
    private String token;
    private String refreshToken;
}
