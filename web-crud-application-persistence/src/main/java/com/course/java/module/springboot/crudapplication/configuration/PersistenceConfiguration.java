package com.course.java.module.springboot.crudapplication.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.course.java.module.springboot.crudapplication.repository")
@EntityScan(basePackages = "com.course.java.module.springboot.crudapplication.repository.entity")
public class PersistenceConfiguration {
}
