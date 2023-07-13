package com.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Main {

	@PostConstruct
	public void funcionInicial() {
		System.out.println("Hola desde clase inicial");
	}

	@Bean
	public CommandLineRunner funcionSecundaria() {
		return args -> {
			System.out.println("Hola desde clase secundaria");
		};
	}

	@Bean
	public CommandLineRunner terceraFuncion() {
		return args -> {
			System.out.println("Soy la tercera clase");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
