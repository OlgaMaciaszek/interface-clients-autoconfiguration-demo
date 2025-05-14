package com.example.http.verification.client;

import com.example.http.verification.client.clients.FraudVerificationService;
import com.example.http.verification.client.clients.person.CreditScoreVerificationService;
import com.example.http.verification.client.clients.person.PersonVerificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
// CreditScore in both verificationClient and default packages
// Simple client with group configured, types provided
@ImportHttpServices(group = "verificationClient", types = {FraudVerificationService.class, CreditScoreVerificationService.class})
// Simple client with a default group, client with base package scan
@ImportHttpServices(basePackages = "com/example/http/verification/client/clients/person")
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

}

