package com.latinhouse.backend.signup.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSignupWebRequest {
    private String email;
    private String password;
}
