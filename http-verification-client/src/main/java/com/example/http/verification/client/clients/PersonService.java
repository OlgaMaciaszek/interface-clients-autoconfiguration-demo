package com.example.http.verification.client.clients;


import java.util.UUID;

import com.example.http.verification.client.dto.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
@HttpExchange("/persons")
public interface PersonService {

	@GetExchange("/{id}")
	Person getPerson(@PathVariable UUID id);

	@PostExchange
	void add(@RequestBody Person person);

	@GetExchange("/test")
	String test();

	@GetExchange("/test/{id}")
	String test(@PathVariable String id);

	@GetExchange("/{description}/{value}")
	String test(@PathVariable String description, @PathVariable int value);

	@GetExchange("/test/{id}")
	Mono<String> testMono(@PathVariable String id);

	@GetExchange("/test/{id}")
	Mono<String> testMonoThrowable(@PathVariable String id);

	@GetExchange
	Mono<Void> testVoid();

	@GetExchange("/test/{id}")
	Flux<String> testFlux(@PathVariable String id);

	@GetExchange("/test/{id}")
	Flux<String> testFluxThrowable(@PathVariable String id);

}