package com.latinhouse.backend.user.port.in.response;

import com.latinhouse.backend.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAppResponse {
    private String email;

    public UserAppResponse(User user) {
        this.email = user.getEmail();
    }
}
