package com.pandiaaman.bak.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trial")
@Slf4j
public class TrialRestController {

	//http://localhost:8081/oreillyapp/trial/getmapping
	@GetMapping(value="/getmapping", produces="application/json")
	public ResponseEntity<String> trialHandler(){
		log.info("entering into the trialHandler");
		return ResponseEntity.status(HttpStatus.OK).body("checking...");
	}
}
