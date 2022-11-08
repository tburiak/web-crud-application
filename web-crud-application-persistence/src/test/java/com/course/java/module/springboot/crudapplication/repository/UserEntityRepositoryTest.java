package com.course.java.module.springboot.crudapplication.repository;


import com.course.java.module.springboot.crudapplication.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserEntityRepositoryTest extends  BasePersistenceTest<UserEntityRepository>{
    private static final int ID = 1;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String GENDER = "M";
    private static final LocalDateTime NOW = LocalDateTime.now(Clock.systemUTC());


    @Test
    void shouldSaveEntity() {
        UserEntity userEntity = buildUserEntity();
        repository.save(userEntity);
        assertThat(repository.findById(ID)).hasValue(userEntity);
    }

    private UserEntity buildUserEntity() {
        return UserEntity.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(NOW)
                .gender(GENDER)
                .build();
    }

}