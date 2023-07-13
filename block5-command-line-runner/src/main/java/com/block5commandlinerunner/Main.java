package com.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {

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
/**
	@Bean
	public CommandLineRunner terceraFuncion() {
		return args -> {
			System.out.println("Soy la tercera clase");
		};
	}
**/

	@Bean
	public CommandLineRunner terceraFuncion() {
		return args -> {
			System.out.println("Soy la tercera clase");
			if (args.length > 0) {
				System.out.println("Valores pasados como parámetro:");
				for (String arg : args) {
					System.out.println(arg);
				}
			} else {
				System.out.println("No se pasaron valores como parámetro.");
			}
		};
	}

	/**
	apuntando al directorio target
	java -jar block5-command-line-runner-1.0-SNAPSHOT.jar valor1 valor2 valor3
	**/


	public static void main(String[] args) {
		log.info("Iniciando aplicación");
		SpringApplication.run(Main.class, args);
		log.info("Aplicación finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Estamos dentro del Runner");

	}
}
