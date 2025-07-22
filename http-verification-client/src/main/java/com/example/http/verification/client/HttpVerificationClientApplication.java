package com.example.http.verification.client;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;
import com.example.http.verification.client.dto.Person;
import com.example.http.verification.client.fallback.EmptyFallbacks;
import com.example.http.verification.client.fallback.VerificationClientFallbacks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(group = "verificationClient", types = {VerificationService.class,
		PersonService.class})
@HttpServiceFallback(EmptyFallbacks.class)
@HttpServiceFallback(value = VerificationClientFallbacks.class, forGroup = "verificationClient", forService = PersonService.class)
//@HttpServiceFallback(value = VerificationClientFallbacks.class, forGroup = "verificationClient", forService = VerificationService.class)
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}


	@Bean
	public CommandLineRunner runner(PersonService service) {
		return args -> {
			try {
				service.test("test", 5);
				System.err.println(service.test("test!!!"));
				service.add(new Person("Kate", "Thomas", "kate.thomas@gmail.com"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}

