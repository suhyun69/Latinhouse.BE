package com.latinhouse.backend.user.adapter.out.persistence;

import com.latinhouse.backend.infrastructure.persistence.user.UserJpaEntity;
import com.latinhouse.backend.infrastructure.persistence.user.UserMapper;
import com.latinhouse.backend.infrastructure.persistence.user.UserRepository;
import com.latinhouse.backend.user.domain.User;
import com.latinhouse.backend.user.port.out.ReadUserPort;
import com.latinhouse.backend.user.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements ReadUserPort, UpdateUserPort {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::mapToDomainEntity);
    }

    @Override
    public User update(User user) {
        UserJpaEntity userT = userMapper.mapToJpaEntity(user);
        UserJpaEntity updated = userRepository.save(userT);
        return userMapper.mapToDomainEntity(updated);
    }
}
