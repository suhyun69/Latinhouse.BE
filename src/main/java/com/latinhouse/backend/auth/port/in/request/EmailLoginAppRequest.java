package com.latinhouse.backend.auth.port.in.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmailLoginAppRequest {
    String email;
    String password;
}
