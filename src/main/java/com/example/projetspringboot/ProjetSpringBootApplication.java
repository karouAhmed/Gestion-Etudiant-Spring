package com.example.projetspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetSpringBootApplication.class, args);
	}

}
