package com.example.http.verification.client.controllers;

import java.util.UUID;

import com.example.http.verification.client.clients.PersonVerificationService;
import com.example.http.verification.client.dto.Person;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping("/persons")
class PersonController {

	private final PersonVerificationService personVerificationService;

	PersonController(PersonVerificationService personVerificationService) {
		this.personVerificationService = personVerificationService;
	}

	@GetMapping("/{id}")
	public Mono<Person> getPerson(@PathVariable UUID id) {
		return personVerificationService.getPerson(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Void> add() {
		return personVerificationService.add(new Person("Tom", "Connor", "t.connor@example.com"));
	}

}