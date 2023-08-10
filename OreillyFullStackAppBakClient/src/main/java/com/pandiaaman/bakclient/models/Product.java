package com.pandiaaman.bakclient.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Product {

	
	private String productId;
	
	private String productName;
	
	private double productPrice;
}
