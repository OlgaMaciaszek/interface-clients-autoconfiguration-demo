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
import org.springframework.web.service.registry.HttpServiceGroup;
import org.springframework.web.service.registry.ImportHttpServices;


@SpringBootApplication
@ImportHttpServices(group = "verificationClient", types = {VerificationService.class,
		PersonService.class}, clientType = HttpServiceGroup.ClientType.WEB_CLIENT)
@HttpServiceFallback(EmptyFallbacks.class)
@HttpServiceFallback(value = VerificationClientFallbacks.class, forGroup = "verificationClient", forService = VerificationService.class)
@HttpServiceFallback(value = VerificationClientFallbacks.class, forGroup = "verificationClient", forService = PersonService.class)
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
				System.err.println(service.testMono("test!!!").block());
				System.err.println(service.testMonoThrowable("test!!!")
						.block());
				System.err.println(service.testVoid().block());
				System.err.println(service.testFlux("test!!!").blockFirst());
				System.err.println(service.testFluxThrowable("test!!!")
						.blockFirst());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

}

