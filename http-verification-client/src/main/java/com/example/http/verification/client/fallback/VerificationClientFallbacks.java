package com.example.http.verification.client.fallback;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;
import com.example.http.verification.client.dto.Person;
import com.example.http.verification.client.dto.VerificationRequest;
import com.example.http.verification.client.dto.VerificationResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilderFactory;

/**
 * @author Olga Maciaszek-Sharma
 */
public class VerificationClientFallbacks {

//	VerificationClientFallbacks(String test) {
//	}


	public Person getPerson(UUID id) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public void add(Person person) {
		System.err.println("Fallback person: " + person);
	}


	public int count(Optional<URI> uri) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public int countFactory(Optional<UriBuilderFactory> factory) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public int countWithMetaAnnotation() {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public int countWithMethod(Optional<HttpMethod> method) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String test(String description, int value) {
		return description + ": " + value;
	}


	public String test(Throwable cause, String test) {
		return "With cause: " + cause.getMessage() + " With args: " + test;
	}


	public String header(Optional<String> header) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String headers(Map<String, String> headers) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String headerValues(Collection<String> headerValues) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String requestParam(String param) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String requestParams(Map<String, String> params) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String requestParamValues(Collection<String> paramValues) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String cookie(String cookie) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String cookies(Map<String, String> cookie) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String cookieValues(Collection<String> cookieValues) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String pathVariable(String variable) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public VerificationResult verify(Optional<VerificationRequest> request) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String postParts(String part1, HttpEntity<String> part2) {
		throw new UnsupportedOperationException("Please, implement me.");
	}


	public String postMultipart(MultipartFile file) {
		throw new UnsupportedOperationException("Please, implement me.");
	}
}
