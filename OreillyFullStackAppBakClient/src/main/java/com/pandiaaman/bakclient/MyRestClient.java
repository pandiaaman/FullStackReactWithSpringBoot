package com.pandiaaman.bakclient;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pandiaaman.bakclient.models.Product;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyRestClient {

	//make sure that the server side is running in test profile in application.yml
	private String baseAddress = "http://localhost:8081/oreillytestapp/products/";
	
	@Autowired
	private RestTemplate template; //bean defined in SpringClientProjectConfig
	
	public boolean demoInsertProduct(String name, double price) {
		Product prod = new Product("-1", name, price);
		
		ResponseEntity<Product> response = template.postForEntity(baseAddress, prod, Product.class);
		log.info("status code :: {}", response.getStatusCode());
		log.info("body :: {}", response.getBody());
		return true;
	}
	
	public void demoGetOneProductById(String productId) {
		log.info("getting one product by id");
		ResponseEntity<Product> response = template.getForEntity((baseAddress + productId), Product.class);
		log.info("status code :: {}", response.getStatusCode());
		log.info("body :: {}", response.getBody());
	}
	
	public void demoUpdateProductById(String productId) {
		Product prod = template.getForObject((baseAddress + productId), Product.class);
		prod.setProductPrice(prod.getProductPrice()*2);
		
		template.put(baseAddress, prod); //to update we use update
		//above returns void if all goes good,else throws an exception
		log.info("updated PRODUCT!!!");
	}
	
	public void getAllProducts() {
		//ISSUE : 
		/*
		 * List<Product> can not have a .class hence to get the results we first need a type
		 * that can be used in the get method, hence we need to use ParameterizedTypeReference
		 */
		ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>() {};
		
		ResponseEntity<List<Product>> response = template.exchange(baseAddress, HttpMethod.GET, null, responseType);
		
		log.info("list of all products");
		for(Product p : response.getBody()) {
			log.info(baseAddress);
		}
	}
	
}
