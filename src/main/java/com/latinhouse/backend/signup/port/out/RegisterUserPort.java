package com.latinhouse.backend.signup.port.out;

import com.latinhouse.backend.user.domain.User;

public interface RegisterUserPort {
    User register(User user);
}
