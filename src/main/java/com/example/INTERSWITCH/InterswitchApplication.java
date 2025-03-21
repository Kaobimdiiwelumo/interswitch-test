package com.example.INTERSWITCH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.INTERSWITCH.REPOSITORY")
public class InterswitchApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterswitchApplication.class, args);
	}

}
