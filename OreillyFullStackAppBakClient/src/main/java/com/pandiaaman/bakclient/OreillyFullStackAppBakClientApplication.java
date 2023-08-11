package com.pandiaaman.bakclient;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OreillyFullStackAppBakClientApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(OreillyFullStackAppBakClientApplication.class, args);
		
		log.info("client app started");
		
		MyRestClient client = context.getBean(MyRestClient.class);
		client.demoInsertProduct("productFromClient", 1000);
		client.demoGetOneProductById("d5f30857-8731-486b-afe6-9284ab922dad");
		client.getAllProducts();
	}

}
