package com.example.http.verification.client.clients;

import org.springframework.web.service.annotation.GetExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
public interface TestService {

	@GetExchange("/test")
	String test();
}
