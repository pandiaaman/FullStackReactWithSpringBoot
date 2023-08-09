package com.pandiaaman.bak.components;

import java.time.LocalTime;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("testComponent")
@Scope("singleton") //same by default
@Lazy //lazy means that bean will only be instantiated when called(not by default on app startup)
@Slf4j
public class TrialCheckComponent {
	
	private LocalTime ts = LocalTime.now();
	private static int counter = 0;
	
	
	public TrialCheckComponent() {
		log.info("application component is getting created {} ",counter++);
		log.info("application starting at {}",ts); 
	}
	
	@Override
	public String toString() {
		return "component injection tested";
	}
}
