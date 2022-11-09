package com.course.springboot.webcrudapplication.persistence.repository;


import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;

class UserEntityRepositoryTest extends  BasePersistenceTest<UserEntityRepository>{
    private static final int ID = 1;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String GENDER = "M";
    private static final LocalDate NOW = LocalDate.now(Clock.systemUTC());


    @Test
    void shouldSaveEntity() {
        UserEntity userEntity = buildUserEntity();
        repository.save(userEntity);
        Assertions.assertThat(repository.findById(ID)).hasValue(userEntity);
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