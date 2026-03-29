package com.latinhouse.backend.auth.port.in;

import jakarta.servlet.http.HttpServletRequest;

public interface LogoutUseCase {
    void logout(HttpServletRequest request);
}

