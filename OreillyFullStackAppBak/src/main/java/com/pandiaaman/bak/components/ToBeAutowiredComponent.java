package com.pandiaaman.bak.components;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Lazy
@Slf4j
public class ToBeAutowiredComponent {

	private int value;
	
	public ToBeAutowiredComponent() {
		log.info("initiating component to be autowired");
		this.value = 5;
	}
	
	public int getValue() {
		log.info("getting the value");
		return this.value;
	}
}
