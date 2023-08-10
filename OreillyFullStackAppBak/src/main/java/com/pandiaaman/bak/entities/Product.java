package com.pandiaaman.bak.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="product_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="product_id")
	private String productId;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_price")
	private double productPrice;
}
