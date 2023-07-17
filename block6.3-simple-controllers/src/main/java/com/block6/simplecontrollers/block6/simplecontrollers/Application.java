package com.block6.simplecontrollers.block6.simplecontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Qualifier("bean1")
	public Persona persona1() {
		return new Persona("bean1");
	}

	@Bean
	@Qualifier("bean2")
	public Persona persona2() {
		return new Persona("bean2");
	}

	@Bean
	@Qualifier("bean3")
	public Persona persona3() {
		return new Persona("bean3");
	}
}
