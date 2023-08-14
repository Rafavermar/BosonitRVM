package com.block16springcloud.block16springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Block16SpringCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16SpringCloudApplication.class, args);
	}

}
