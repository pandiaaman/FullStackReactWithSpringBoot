package com.pandiaaman.bak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pandiaaman.bak.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
