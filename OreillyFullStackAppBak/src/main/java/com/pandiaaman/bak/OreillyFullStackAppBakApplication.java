package com.pandiaaman.bak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/*
 * @springBootApplication contains three annotations : 
 * 	@Configuration
 * 	@ComponentScan
 * 	@EnableConfiguration
 */
@SpringBootApplication
@Slf4j
public class OreillyFullStackAppBakApplication {

	public static void main(String[] args) {
		SpringApplication.run(OreillyFullStackAppBakApplication.class, args);
		
		log.info("*************************************************");
		log.info("bak app started");
		log.info("*************************************************");
	}

}
