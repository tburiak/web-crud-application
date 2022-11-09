package com.course.springboot.webcrudapplication.service;

import com.course.springboot.webcrudapplication.model.UserRequest;
import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(int id);
    Optional<UserEntity> createUser(UserRequest user);
    Optional<UserEntity> updateUser(int id, UserRequest user);
    boolean removeUserById(int id);
}
