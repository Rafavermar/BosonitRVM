package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Block16SpringCloudBackendFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16SpringCloudBackendFrontendApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
