package com.pandiaaman.bak.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pandiaaman.bak.exceptions.NoProductFoundException;
import com.pandiaaman.bak.exceptions.NoProductsAvailableException;
import com.pandiaaman.bak.payloads.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ProductExceptionAdvisor {

	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<ApiResponse> handleNoProductFoundException(NoProductFoundException ex){
		log.info("handling the no product found exeption");
		
		ApiResponse res =  ApiResponse.builder()
				.msg(ex.getMessage())
				.status(HttpStatus.NO_CONTENT)
				.success(false)
				.build();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}
	
	@ExceptionHandler(NoProductsAvailableException.class)
	public ResponseEntity<ApiResponse> handleNoProductsAvailableException(NoProductsAvailableException ex){
		log.info("handling NoProductsAvailableException");
		
		ApiResponse res =  ApiResponse.builder()
				.msg(ex.getMessage())
				.status(HttpStatus.NO_CONTENT)
				.success(false)
				.build();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		
	}
	
}
