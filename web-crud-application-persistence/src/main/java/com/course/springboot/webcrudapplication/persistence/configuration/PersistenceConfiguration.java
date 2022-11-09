package com.course.springboot.webcrudapplication.persistence.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.course.springboot.webcrudapplication.persistence.repository")
@EntityScan(basePackages = "com.course.springboot.webcrudapplication.persistence.repository.entity")
public class PersistenceConfiguration {
}
