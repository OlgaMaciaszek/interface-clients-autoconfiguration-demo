package com.example.http.verification.client;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(value = "verificationClient", httpServiceTypes = {VerificationService.class,
		PersonService.class})
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(PersonService service) {
		return args -> {
			try {
				System.err.println(service.test());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}

