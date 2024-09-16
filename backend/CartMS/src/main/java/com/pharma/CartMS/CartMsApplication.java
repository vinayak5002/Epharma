package com.pharma.CartMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CartMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartMsApplication.class, args);
	}

	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}
	
}
