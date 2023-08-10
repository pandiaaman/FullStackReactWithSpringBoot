package com.pandiaaman.bak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandiaaman.bak.entities.Product;
import com.pandiaaman.bak.exceptions.NoProductFoundException;
import com.pandiaaman.bak.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo;
	

	@Override
	public Product addProduct(Product product) {
		log.info("service : : adding product");
		product = this.repo.save(product);
		return product;
	}
	
	@Override
	public List<Product> getAllProducts() {
		log.info("Service : : fetching all products");
		List<Product> fetchedProducts = this.repo.findAll();
		return fetchedProducts;
	}

	@Override
	public Product getProductById(String productId) {
		log.info("Service : : fetching product by id");
		Product fetchedProduct = this.repo.findById(productId).orElseThrow(() -> new NoProductFoundException("no such product with given id is found"));
		return fetchedProduct;
	}

	@Override
	public Product updateProductForId(String productId, Product product) {
		log.info("Service : : updating product by id");
		Product fetchedProduct = this.getProductById(productId);
		
		fetchedProduct.setProductName(product.getProductName());
		fetchedProduct.setProductPrice(product.getProductPrice());
		
		return fetchedProduct;
	}

	@Override
	public Product deleteProduct(String productId) {
		log.info("Service : : deleting product");
		Product fetchedProduct = this.getProductById(productId);
		this.repo.deleteById(productId);
		return fetchedProduct;
	}


}
