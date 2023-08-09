package com.pandiaaman.bak.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trial")
public class TrialRestController {

	@GetMapping("/getmapping")
	public ResponseEntity<String> trialHandler(){
		return ResponseEntity.status(HttpStatus.OK).body("checkcing...");
	}
}
