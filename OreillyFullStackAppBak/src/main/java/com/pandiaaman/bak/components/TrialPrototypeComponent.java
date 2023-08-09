package com.pandiaaman.bak.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("protoComp")
@Scope("prototype")
@Slf4j
public class TrialPrototypeComponent {

	private static int counter = 0;
	
	public TrialPrototypeComponent() {
		log.info("prototype component created : {} ", counter++);
	}
}
