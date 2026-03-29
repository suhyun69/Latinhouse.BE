package com.latinhouse.backend.security;

import com.latinhouse.backend.user.adapter.out.persistence.entity.UserJpaEntity;
import com.latinhouse.backend.user.adapter.out.persistence.repository.UserRepository;
import com.latinhouse.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJpaEntity userT = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                userT.getEmail(), userT.getPassword(), new ArrayList<>());
    }
}

