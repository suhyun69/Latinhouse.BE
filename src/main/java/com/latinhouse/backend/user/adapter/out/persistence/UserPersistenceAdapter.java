package com.latinhouse.backend.user.adapter.out.persistence;

import com.latinhouse.backend.user.adapter.out.persistence.entity.UserJpaEntity;
import com.latinhouse.backend.user.adapter.out.persistence.mapper.UserMapper;
import com.latinhouse.backend.user.adapter.out.persistence.repository.UserRepository;
import com.latinhouse.backend.user.domain.User;
import com.latinhouse.backend.user.port.in.request.EmailSignupAppRequest;
import com.latinhouse.backend.user.port.out.CreateUserPort;
import com.latinhouse.backend.user.port.out.ReadUserPort;
import com.latinhouse.backend.user.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort
        , ReadUserPort
        , UpdateUserPort
{

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User create(EmailSignupAppRequest appReq) {

        UserJpaEntity userT = UserJpaEntity.builder()
                .email(appReq.getEmail())
                .password(appReq.getPassword())
                .build();
        return userMapper.mapToDomainEntity(userRepository.save(userT));
    }

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
