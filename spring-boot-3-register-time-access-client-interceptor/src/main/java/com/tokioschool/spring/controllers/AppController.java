package com.tokioschool.spring.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AppController {

	@GetMapping("/foo")
	public ResponseEntity<?> fooHandler() {
		Map<String, Object> data = new HashMap<>();
		data.put("title", "Bienvendios al sistesma de authentication");
		data.put("time", LocalDateTime.now());
		
		return ResponseEntity.ok(data);
	}

}
