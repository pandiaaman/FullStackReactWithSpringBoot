package com.pandiaaman.bak.services;

import java.util.List;

import com.pandiaaman.bak.entities.Product;

public interface ProductService {

	Product addProduct(Product product);
	
	List<Product> getAllProducts();
	
	Product getProductById(String productId);
	
	Product updateProductForId(String productId, Product product);
	
	Product deleteProduct(String productId);
}
