package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder paswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


}
