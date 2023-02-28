package com.example.demo2;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class Demo2Application {
	/**
	 * Application Main class.
	 * @param args arguments passed to the main class.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

}
