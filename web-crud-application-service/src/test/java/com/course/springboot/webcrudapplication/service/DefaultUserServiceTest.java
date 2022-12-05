package com.course.springboot.webcrudapplication.service;

import com.course.springboot.webcrudapplication.model.UserRequest;
import com.course.springboot.webcrudapplication.persistence.repository.UserEntityRepository;
import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {
    public static final UserRequest USER_REQUEST = UserRequest.builder()
            .firstName("TestFirstName")
            .lastName("TestLastName")
            .gender("M")
            .birthDate(LocalDate.now(Clock.systemUTC()))
            .build();
    private static final UserEntity USER_ENTITY_1 = UserEntity.builder()
            .id(1)
            .firstName("FirstUser")
            .lastName("FirstUserLastName")
            .birthDate(LocalDate.now(Clock.systemUTC()))
            .gender("M")
            .build();
    private static final UserEntity USER_ENTITY_2 = UserEntity.builder()
            .id(1)
            .firstName("SecondUser")
            .lastName("SecondUserLastName")
            .birthDate(LocalDate.now(Clock.systemUTC()))
            .gender("F")
            .build();
    @Mock
    private UserEntityRepository userEntityRepository;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void shouldReturnAllUsers() {
        var userEntities = List.of(USER_ENTITY_1, USER_ENTITY_2);
        Mockito.when(userEntityRepository.findAll()).thenReturn(userEntities);

        assertThat(userService.getAllUsers()).isEqualTo(userEntities);
        verify(userEntityRepository).findAll();
    }

    @Test
    void shouldReturnUserById() {
        var optionalUserEntity = Optional.of(USER_ENTITY_1);
        Mockito.when(userEntityRepository.findById(1)).thenReturn(optionalUserEntity);

        assertThat(userService.getUserById(1)).isEqualTo(optionalUserEntity);
        verify(userEntityRepository).findById(1);
    }

    @Test
    void shouldCreateUser() {
        UserEntity userEntity = buildUserEntityByRequest(USER_REQUEST);
        when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        assertThat(userService.createUser(USER_REQUEST)).isEqualTo(Optional.of(userEntity));
        verify(userEntityRepository).save(any(UserEntity.class));
    }

    @Test
    void shouldUpdateUser() {
        var optionalUserEntity = Optional.of(USER_ENTITY_1);
        UserEntity userEntity = buildUserEntityByRequest(USER_REQUEST);
        when(userEntityRepository.findById(1)).thenReturn(optionalUserEntity);
        when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        assertThat(userService.updateUser(1, USER_REQUEST)).isEqualTo(Optional.of(userEntity));
        verify(userEntityRepository).findById(1);
        verify(userEntityRepository).save(any(UserEntity.class));
    }

    @Test
    void shouldRemoveUserById() {
        when(userEntityRepository.findById(1)).thenReturn(Optional.empty());

        assertThat(userService.removeUserById(1)).isEqualTo(Boolean.TRUE);
        verify(userEntityRepository).deleteById(1);
    }

    private static UserEntity buildUserEntityByRequest(UserRequest userRequest) {
        return UserEntity.builder()
                .lastName(userRequest.getLastName())
                .firstName(userRequest.getFirstName())
                .birthDate(userRequest.getBirthDate())
                .gender(userRequest.getGender())
                .build();
    }
}