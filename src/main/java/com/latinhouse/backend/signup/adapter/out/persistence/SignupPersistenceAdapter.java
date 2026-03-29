package com.latinhouse.backend.signup.adapter.out.persistence;

import com.latinhouse.backend.infrastructure.persistence.user.UserJpaEntity;
import com.latinhouse.backend.infrastructure.persistence.user.UserMapper;
import com.latinhouse.backend.infrastructure.persistence.user.UserRepository;
import com.latinhouse.backend.signup.port.out.CheckDuplicateEmailPort;
import com.latinhouse.backend.signup.port.out.RegisterUserPort;
import com.latinhouse.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupPersistenceAdapter implements RegisterUserPort, CheckDuplicateEmailPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User register(User user) {
        UserJpaEntity entity = userMapper.mapToJpaEntity(user);
        return userMapper.mapToDomainEntity(userRepository.save(entity));
    }
}
