package com.latinhouse.backend.signup.port.in;

import com.latinhouse.backend.signup.port.in.request.EmailSignupAppRequest;
import com.latinhouse.backend.signup.port.in.response.SignupAppResponse;

public interface SignupUseCase {
    SignupAppResponse emailSignup(EmailSignupAppRequest appReq);
}
