package com.block14springsecurity.block14springsecurity;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Block14SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block14SpringSecurityApplication.class, args);
	}

	@Configuration
	public class PasswordEncoderTest {

		private final PasswordEncoder passwordEncoder;

		public PasswordEncoderTest( PasswordEncoder passwordEncoder ) {
			this.passwordEncoder = passwordEncoder;
		}

		@PostConstruct
		public void encodePassword() {
			String rawPassword = "12345";  // replace with your raw password
			String encodedPassword = passwordEncoder.encode(rawPassword);
			System.out.println("Encoded password: " + encodedPassword);
		}
	}
}