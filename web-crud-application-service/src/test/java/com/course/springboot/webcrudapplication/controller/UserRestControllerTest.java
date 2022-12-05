package com.course.springboot.webcrudapplication.controller;

import com.course.springboot.webcrudapplication.model.UserRequest;
import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;
import com.course.springboot.webcrudapplication.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(printOnlyOnFailure = false, addFilters = false)
@ActiveProfiles("test")
class UserRestControllerTest {

    private static final String API_USERS_URL = "/api/users";
    private static final String API_USERS_ID_URL = "/api/users/{id}";
    private static final int ID = 1;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String GENDER = "M";

    UserRequest USER_REQUEST = UserRequest.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .gender(GENDER)
            .birthDate(LocalDate.now(Clock.systemUTC()))
            .build();

    UserEntity USER_ENTITY = UserEntity.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .gender(GENDER)
            .birthDate(LocalDate.now(Clock.systemUTC()))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;


    @Test
    void getAllUsersStatusOk() throws Exception {
        mockMvc.perform(get(API_USERS_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        verify(userService).getAllUsers();
    }

    @Test
    void getUserByIdStatusOk() throws Exception {
        Optional<UserEntity> userEntityOptional = Optional.of(UserEntity.builder().id(ID).build());
        when(userService.getUserById(ID)).thenReturn(userEntityOptional);
        mockMvc.perform(get(API_USERS_ID_URL, 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        verify(userService).getUserById(ID);
    }

    @Test
    void getUserByIdStatusBadRequest() throws Exception {
        when(userService.getUserById(ID)).thenReturn(Optional.empty());
        mockMvc.perform(get(API_USERS_ID_URL, 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
        verify(userService).getUserById(ID);
    }

    @Test
    void createUserStatusOk() throws Exception {
        when(userService.createUser(USER_REQUEST)).thenReturn(Optional.of(USER_ENTITY));
        mockMvc.perform(post(API_USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(USER_REQUEST)))
                .andExpect(status().isCreated());
        verify(userService).createUser(USER_REQUEST);
    }

    @Test
    void createUserStatusBadRequest() throws Exception {
        when(userService.createUser(USER_REQUEST)).thenReturn(Optional.empty());
        mockMvc.perform(post(API_USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(USER_REQUEST)))
                .andExpect(status().isBadRequest());
        verify(userService).createUser(USER_REQUEST);
    }

    @Test
    void updateUserStatusOk() throws Exception {
        var userRequestToUpdate = USER_REQUEST.toBuilder().firstName("Tom").build();
        var updatedUserEntity = USER_ENTITY.toBuilder().firstName("Tom").build();
        when(userService.updateUser(ID, userRequestToUpdate)).thenReturn(Optional.of(updatedUserEntity));
        mockMvc.perform(put(API_USERS_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(userRequestToUpdate)))
                .andExpect(status().isOk());
        verify(userService).updateUser(ID, userRequestToUpdate);
    }

    @Test
    void updateUserStatusBadRequest() throws Exception {
        var userRequestToUpdate = USER_REQUEST.toBuilder().firstName("Tom").build();
        when(userService.updateUser(ID, userRequestToUpdate)).thenReturn(Optional.empty());
        mockMvc.perform(put(API_USERS_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(userRequestToUpdate)))
                .andExpect(status().isBadRequest());
        verify(userService).updateUser(ID, userRequestToUpdate);
    }

    @Test
    void deleteUserTrueResponse() throws Exception {
        when(userService.removeUserById(ID)).thenReturn(true);
        var response = mockMvc.perform(delete(API_USERS_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(response).contains("{\"deleted\":true}");
        verify(userService).removeUserById(ID);
    }

    @Test
    void deleteUserFalseResponse() throws Exception {
        when(userService.removeUserById(ID)).thenReturn(false);
        var response = mockMvc.perform(delete(API_USERS_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(response).contains("{\"deleted\":false}");
        verify(userService).removeUserById(ID);
    }

}