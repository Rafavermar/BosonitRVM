package com.block16APIGETWAY.block16APIGETWAY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Block16ApiGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16ApiGetwayApplication.class, args);
	}

}
