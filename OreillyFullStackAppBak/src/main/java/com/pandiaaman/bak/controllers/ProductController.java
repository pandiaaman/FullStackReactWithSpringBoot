package com.pandiaaman.bak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandiaaman.bak.entities.Product;
import com.pandiaaman.bak.exceptions.NoProductsAvailableException;
import com.pandiaaman.bak.services.ProductServiceImpl;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductServiceImpl service;
	
	@PostMapping(value="/",
			produces= {"application/json","application/xml"},
			consumes= {"application/json","application/xml"})
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		Product productAdded = this.service.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productAdded);
	}
	
	@GetMapping(value="/", produces= {"application/json","application/xml"})	
	public ResponseEntity<List<Product>> getAllProducts(){
		try {
			List<Product> fetchedProducts = this.service.getAllProducts();
			if(fetchedProducts.size() == 0) {
				throw new NoProductsAvailableException("No Products available in the system");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(fetchedProducts);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@GetMapping(value="/{prodId}")
	public ResponseEntity<Product> getProductById(@PathVariable("prodId") String prodId){
		try {
			Product fetchdProd = service.getProductById(prodId);
			
			return ResponseEntity.status(HttpStatus.OK).body(fetchdProd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
