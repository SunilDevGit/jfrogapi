package com.sacumen.jfrog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SacumenJfrogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SacumenJfrogServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	

}
