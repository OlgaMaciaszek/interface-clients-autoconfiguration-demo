package com.example.http.verification.client;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.interfaceclients.http.InterfaceClientGroup;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@InterfaceClientGroup(value = "verificationClient", httpServiceTypes = {VerificationService.class})
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(PersonService service) {
		return args -> {
			System.err.println(service.test());
		};
	}

}

