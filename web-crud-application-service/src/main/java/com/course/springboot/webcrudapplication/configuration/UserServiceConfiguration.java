package com.course.springboot.webcrudapplication.configuration;

import com.course.springboot.webcrudapplication.persistence.repository.UserEntityRepository;
import com.course.springboot.webcrudapplication.service.DefaultUserService;
import com.course.springboot.webcrudapplication.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserService userService(UserEntityRepository userEntityRepository){
        return new DefaultUserService(userEntityRepository);
    }
}
