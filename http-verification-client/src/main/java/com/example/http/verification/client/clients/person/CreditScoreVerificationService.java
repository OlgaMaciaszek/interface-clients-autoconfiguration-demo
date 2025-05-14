package com.example.http.verification.client.clients.person;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.http.verification.client.dto.Person;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
@HttpExchange("/credit")
public interface CreditScoreVerificationService {

	@GetExchange("/{id}")
	BigDecimal getScore(@PathVariable UUID id);

}
