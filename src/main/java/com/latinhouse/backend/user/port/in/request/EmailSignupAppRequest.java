package com.latinhouse.backend.user.port.in.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmailSignupAppRequest {
    String email;
    String password;
}
