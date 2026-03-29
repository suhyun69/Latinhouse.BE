package com.latinhouse.backend.user.port.in;

import com.latinhouse.backend.user.port.in.request.UpdateUserAppRequest;
import com.latinhouse.backend.user.port.in.response.UserAppResponse;

public interface UpdateUserUseCase {
    UserAppResponse update(String id, UpdateUserAppRequest appReq);
}
