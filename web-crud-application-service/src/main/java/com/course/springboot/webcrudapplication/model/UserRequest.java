package com.course.springboot.webcrudapplication.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class UserRequest {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
}
