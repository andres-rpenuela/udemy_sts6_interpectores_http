package com.tokioschool.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/some")
public class SomeApiController {

	
	@GetMapping("/foo")
	public String fooHandler() {
		return "Resulto of reqets /foo";
	}
	
	@GetMapping("/bar")
	public String barHandler() {
		return "Resulto of reqets /bar";
	}
	
	@GetMapping("/error")
	public String errorHandler() {
		return "Resulto of reqets /error";
	}
}
