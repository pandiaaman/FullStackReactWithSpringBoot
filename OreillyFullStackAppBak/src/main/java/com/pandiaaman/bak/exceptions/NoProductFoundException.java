package com.pandiaaman.bak.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoProductFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoProductFoundException(String msg) {
		super(msg);
		log.error("no product exception initiated");
	}
}
