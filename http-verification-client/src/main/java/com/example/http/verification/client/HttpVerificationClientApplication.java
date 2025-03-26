package com.example.http.verification.client;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;
import com.example.http.verification.client.config.LoadBalancerRestClientHttpServiceGroupConfigurer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.DeferringLoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(value = "verificationClient", httpServiceTypes = {VerificationService.class,
		PersonService.class})
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

	@Bean
	@Order
	LoadBalancerRestClientHttpServiceGroupConfigurer configurer(DeferringLoadBalancerInterceptor loadBalancerInterceptor) {
		return new LoadBalancerRestClientHttpServiceGroupConfigurer(loadBalancerInterceptor);
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

