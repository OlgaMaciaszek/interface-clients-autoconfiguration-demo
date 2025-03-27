package com.example.http.verification.client;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;
import com.example.http.verification.client.config.LoadBalancerWebClientHttpServiceGroupConfigurer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.interfaceclients.http.HttpInterfaceGroupsProperties;
import org.springframework.cloud.client.loadbalancer.reactive.DeferringLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.service.registry.HttpServiceGroup;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(value = "verificationClient", httpServiceTypes = {VerificationService.class,
		PersonService.class}, clientType = HttpServiceGroup.ClientType.WEB_CLIENT)
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

	@Bean
	@Order
	LoadBalancerWebClientHttpServiceGroupConfigurer configurer(DeferringLoadBalancerExchangeFilterFunction filterFunction,
			HttpInterfaceGroupsProperties properties) {
		return new LoadBalancerWebClientHttpServiceGroupConfigurer(filterFunction,
				properties);
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

