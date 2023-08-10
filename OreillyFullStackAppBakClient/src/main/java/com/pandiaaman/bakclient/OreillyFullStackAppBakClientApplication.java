package com.pandiaaman.bakclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OreillyFullStackAppBakClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(OreillyFullStackAppBakClientApplication.class, args);
		
		log.info("client app started");
	}

}
