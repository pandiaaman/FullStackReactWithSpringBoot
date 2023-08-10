package com.pandiaaman.bakclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pandiaaman.bakclient.models.Product;

@Component
public class MyRestClient {

	private String baseAddress = "http://localhost:8081/products/";
	
	@Autowired
	private RestTemplate template;
	
	public boolean demoInsertProduct(String name, double price) {
		Product prod = new Product("-1", name, price);
		
		ResponseEntity<Product> response = template.postForEntity(baseAddress, prod, Product.class);
		return true;
	}
}
