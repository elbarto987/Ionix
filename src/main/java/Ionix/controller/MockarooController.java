package Ionix.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ionix.service.MockarooService;

@RestController
@RequestMapping("/api/mockaroo")
public class MockarooController {

	@Autowired
	private MockarooService mockarooService;
	
	@PostMapping
	public ResponseEntity<?> api(){
		return ResponseEntity.ok(mockarooService.objecJson());
	}
}
