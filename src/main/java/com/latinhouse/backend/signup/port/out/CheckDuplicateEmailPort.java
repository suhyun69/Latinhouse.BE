package com.latinhouse.backend.signup.port.out;

public interface CheckDuplicateEmailPort {
    boolean existsByEmail(String email);
}
