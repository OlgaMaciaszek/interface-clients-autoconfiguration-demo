package com.example.http.verification.client.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.http.verification.client.clients.FraudVerificationService;
import com.example.http.verification.client.dto.VerificationRequest;
import com.example.http.verification.client.dto.VerificationResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping
public class VerificationClientController {


	private final FraudVerificationService fraudVerificationService;

	public VerificationClientController(FraudVerificationService fraudVerificationService) {
		this.fraudVerificationService = fraudVerificationService;
	}

	@RequestMapping("/count")
	int count() {
		return fraudVerificationService.count(Optional.of(URI.create("http://localhost:8081/count")));
	}

	@RequestMapping("/count/factory")
	int countFactory() {
		return fraudVerificationService.countFactory(Optional.of(new DefaultUriBuilderFactory("http://localhost:8081/count")));
	}

	@GetMapping("/count/meta")
	int countWithMetaAnnotation(URI uri) {
		return fraudVerificationService.countWithMetaAnnotation();
	}

	@RequestMapping("/count/method")
	int countWithMethod() {
		return fraudVerificationService.countWithMethod(Optional.empty());
	}

	@GetMapping("/test")
	String test() {
		return fraudVerificationService.test();
	}

	@PostMapping()
	VerificationResult verify() {
		return fraudVerificationService.verify(Optional.of(new VerificationRequest("Anna", "Smith")));
	}

	@GetMapping("/header")
	String header() {
		return fraudVerificationService.header(Optional.of("headerValue"));
	}

	@GetMapping("/headers")
	String headers() {
		return fraudVerificationService.headers(Map.of("headerName", "headerValue"));
	}

	@GetMapping("/headerValues")
	String headerValues() {
		return fraudVerificationService.headerValues(List.of("headerValue1", "headerValue2", "headerValue3"));
	}

	@GetMapping("/param")
	String requestParam() {
		return fraudVerificationService.requestParam("param");
	}

	@GetMapping("/params")
	String requestParams() {
		return fraudVerificationService.requestParams(Map.of("paramName", "paramValue"));
	}

	@GetMapping("/paramValues")
	String requestParamValues() {
		return fraudVerificationService.requestParamValues(Set.of("paramValue1", "paramValue2", "paramValue3"));
	}

	@GetMapping("/cookie")
	String cookie() {
		return fraudVerificationService.cookie("cookie");
	}

	@GetMapping("/cookies")
	String cookies() {
		return fraudVerificationService.cookies(Map.of("cookie", "cookieValue"));
	}

	@GetMapping("/cookieValues")
	String cookieValues() {
		return fraudVerificationService.cookieValues(List.of("cookieValue1", "cookieValue2", "cookieValue3"));
	}

	@GetMapping("/path")
	String pathVariable() {
		return fraudVerificationService.pathVariable(null);
	}

	@PostMapping("/parts")
	String postParts() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("testHeader", "testValue");
		HttpEntity<String> part2 = new HttpEntity<>("part2", headers);
		return fraudVerificationService.postParts("part1", part2);
	}

}
