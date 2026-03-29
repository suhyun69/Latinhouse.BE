package com.latinhouse.backend.user.adapter.in.web.response;

import com.latinhouse.backend.user.port.in.response.UserAppResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserWebResponse {
    private String email;

    public UserWebResponse(UserAppResponse appRes) {
        this.email = appRes.getEmail();
    }
}
