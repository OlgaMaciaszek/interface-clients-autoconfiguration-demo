package com.example.http.verification.client.clients;


import java.util.UUID;

import com.example.http.verification.client.dto.Person;
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
public interface PersonVerificationService {

	@GetExchange("/{id}")
	Mono<Person> getPerson(@PathVariable UUID id);

	@PostExchange
	Mono<Void> add(@RequestBody Person person);

}