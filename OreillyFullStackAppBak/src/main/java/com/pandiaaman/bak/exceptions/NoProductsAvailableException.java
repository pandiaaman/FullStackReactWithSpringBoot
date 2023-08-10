package com.pandiaaman.bak.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoProductsAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoProductsAvailableException(String msg) {
		super(msg);
		log.error("error as no products available");
	}
}
