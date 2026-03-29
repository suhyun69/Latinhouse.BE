package com.latinhouse.backend.auth.adapter.out.security;

import com.latinhouse.backend.auth.port.out.CredentialVerifierPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CredentialVerifierAdapter implements CredentialVerifierPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public void verify(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
    }
}
