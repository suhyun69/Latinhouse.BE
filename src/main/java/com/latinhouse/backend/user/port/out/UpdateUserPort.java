package com.latinhouse.backend.user.port.out;

import com.latinhouse.backend.user.domain.User;

public interface UpdateUserPort {
    User update(User user);
}
