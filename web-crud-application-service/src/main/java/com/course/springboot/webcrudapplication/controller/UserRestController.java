package com.course.springboot.webcrudapplication.controller;

import com.course.springboot.webcrudapplication.exception.UserServiceException;
import com.course.springboot.webcrudapplication.model.UserRequest;
import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;
import com.course.springboot.webcrudapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserRestController {
    private final UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserRequest user) {
       Optional<UserEntity> userEntity = userService.createUser(user);
       return userEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.CREATED))
                .orElseThrow(() -> new UserServiceException("Not found user: " + user));
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable int id) {
        Optional<UserEntity> userEntity = userService.getUserById(id);
        return userEntity.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseThrow(() -> new UserServiceException("Not found user with id: " + id));
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable int id, @RequestBody UserRequest user) {
        Optional<UserEntity> userEntity = userService.updateUser(id, user);
        return userEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseThrow(() -> new UserServiceException("Not found user with id: " + id));
    }

    @DeleteMapping(value = "/users/{id}")
    public Map<String, Boolean> deleteUserById(@PathVariable int id) {
        boolean isRemoved = userService.removeUserById(id);
        return Map.of("deleted", isRemoved);
    }
}
