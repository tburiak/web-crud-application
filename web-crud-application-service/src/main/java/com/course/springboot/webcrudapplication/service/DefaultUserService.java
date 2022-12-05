package com.course.springboot.webcrudapplication.service;

import com.course.springboot.webcrudapplication.model.UserRequest;
import com.course.springboot.webcrudapplication.persistence.repository.UserEntityRepository;
import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class DefaultUserService implements UserService{

    private final UserEntityRepository userEntityRepository;

    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }

    public Optional<UserEntity> getUserById(int id) {
        return userEntityRepository.findById(id);
    }
    @Transactional
    public Optional<UserEntity> createUser(UserRequest user) {
        UserEntity userEntity = UserEntity.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .build();
        log.info("Save user with data: {}", userEntity);
        return Optional.of(userEntityRepository.save(userEntity));
    }

    public Optional<UserEntity> updateUser(int id, UserRequest user) {
        Optional<UserEntity> userToUpdate = userEntityRepository.findById(id);
        return userToUpdate.map(entity -> userEntityRepository.save(entity.toBuilder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .build()));
    }

    public boolean removeUserById(int id) {
        log.info("Delete user with id: {}", id);
        userEntityRepository.deleteById(id);
        return userEntityRepository.findById(id).isEmpty();
    }
}
