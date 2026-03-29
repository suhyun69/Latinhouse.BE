package com.latinhouse.backend.user.port.out;

import com.latinhouse.backend.user.domain.User;
import com.latinhouse.backend.user.port.in.request.EmailSignupAppRequest;

public interface CreateUserPort {
    User create(EmailSignupAppRequest appReq);
}
