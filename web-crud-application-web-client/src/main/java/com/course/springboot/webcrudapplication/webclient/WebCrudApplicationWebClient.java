package com.course.springboot.webcrudapplication.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class WebCrudApplicationWebClient {
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> log.info("Web client is starting");
	}

	public static void main(String[] args) {
		SpringApplication.run(WebCrudApplicationWebClient.class, args);
	}

}
