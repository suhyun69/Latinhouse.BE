package com.latinhouse.backend.auth.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailLoginWebRequest {
    private String email;
    private String password;
}
