package com.example.http.verification.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.interfaceclients.http.InterfaceClientGroup;
import org.springframework.context.annotation.Bean;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;

@SpringBootApplication
@InterfaceClientGroup(value = "verificationClient", httpServiceTypes = {VerificationService.class,
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}

