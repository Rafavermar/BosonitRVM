package com.block5profiles.block5profiles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner {

	private final Environment environment;

	public Block5ProfilesApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String activeProfile = environment.getProperty("spring.profiles.active");

		if (activeProfile == null) {
			System.out.println("No se ha proporcionado un perfil activo.");
			return;
		}

		String propertyFile = activeProfile.toLowerCase() + ".properties";
		String bdUrl = environment.getProperty("bd.url");

		if (bdUrl == null) {
			System.out.println("No se ha configurado la propiedad 'bd.url' en el archivo de propiedades.");
			return;
		}

		System.out.println("Perfil activo: " + activeProfile);
		System.out.println("URL de la base de datos: " + bdUrl);
	}
}
