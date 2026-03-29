package com.latinhouse.backend.auth.port.out;

public interface CredentialVerifierPort {
    void verify(String email, String password);
}
