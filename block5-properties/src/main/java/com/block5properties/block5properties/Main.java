package com.block5properties.block5properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@SpringBootApplication
@PropertySource("classpath:application.yml")
public class Main implements CommandLineRunner {

	@Value("${greeting}")
	private String greeting;

	@Value("${my.number}")
	private Integer myNumber;

	@Value("#{systemEnvironment['new_property']}")
	private String newProperty;
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("El valor de greeting es: " + greeting);
			System.out.println("El valor de my.number es: " + myNumber);
			System.out.println("El valor de new.property es: " + newProperty);
		};
	}

	@Override
	public void run(String... args) throws Exception {
		String myUrl = environment.getProperty("MYURL");
		System.out.println("El valor de MYURL es: " + myUrl);
	}
}
