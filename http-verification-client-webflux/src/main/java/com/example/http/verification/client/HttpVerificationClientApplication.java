package com.example.http.verification.client;

import com.example.http.verification.client.clients.FraudVerificationService;
import com.example.http.verification.client.clients.PersonVerificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.HttpServiceGroup;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(group = "verificationClient", types = {FraudVerificationService.class,
		PersonVerificationService.class}, clientType = HttpServiceGroup.ClientType.WEB_CLIENT)
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

}

