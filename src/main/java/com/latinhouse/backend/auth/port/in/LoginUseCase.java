package com.latinhouse.backend.auth.port.in;

import com.latinhouse.backend.auth.port.in.request.EmailLoginAppRequest;
import com.latinhouse.backend.auth.port.in.response.LoginAppResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginUseCase {
    LoginAppResponse emailLogin(EmailLoginAppRequest appReq);
    LoginAppResponse refresh(HttpServletRequest request);
}
