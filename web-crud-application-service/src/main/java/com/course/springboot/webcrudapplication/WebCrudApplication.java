package com.course.springboot.webcrudapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@Slf4j
@SpringBootApplication
public class WebCrudApplication {
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> log.info("hello, world");
	}

	public static void main(String[] args) {
		SpringApplication.run(WebCrudApplication.class, args);
	}

}
