package com.latinhouse.backend.user.port.in;

import com.latinhouse.backend.user.port.in.request.EmailSignupAppRequest;
import com.latinhouse.backend.user.port.in.response.UserAppResponse;

public interface SignupUseCase {
    UserAppResponse emailSignup(EmailSignupAppRequest appReq);
}
