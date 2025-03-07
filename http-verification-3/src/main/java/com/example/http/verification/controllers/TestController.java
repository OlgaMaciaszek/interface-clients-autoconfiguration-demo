package com.example.http.verification.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController("/test")
public class TestController {

	@GetMapping
	String test() {
		return "test";
	}

}
