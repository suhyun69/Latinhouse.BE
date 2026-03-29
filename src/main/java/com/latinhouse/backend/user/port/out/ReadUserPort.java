package com.latinhouse.backend.user.port.out;

import com.latinhouse.backend.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface ReadUserPort {
    List<User> findAll();
    Optional<User> findByEmail(String email);
}
