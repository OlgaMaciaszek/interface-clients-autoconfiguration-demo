package com.example.http.verification.client.clients;

import com.example.http.verification.client.dto.VerificationRequest;
import com.example.http.verification.client.dto.VerificationResult;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
@HttpExchange("/verification")
public interface FraudVerificationService {

	@PostExchange()
	Mono<VerificationResult> verify(@RequestBody VerificationRequest request);

}
