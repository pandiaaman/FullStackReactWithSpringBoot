package com.pandiaaman.bak.components;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Autowired
	private ToBeAutowiredComponent comp;
	
	
	
	//using spring expression language
	@Value("#{ info.getNames.![toUpperCase()]}")
	private List<String> upperNames;
	
	@Value("#{ info.getNames.?[startsWith('a')]}")
	private List<String> allNamesFromA;
	
	@Value("#{ info.getNames.^[startsWith('a')]}")
	private String firstNameWithA;
	
	@Value("#{ info.getNames.$[startsWith('a')]}")
	private String lastNameWithA;
	
	
	public TrialCheckComponent() {
		log.info("application component is getting created {} ",counter++);
		log.info("application starting at {}",ts); 

	}
	
	@Override
	public String toString() {
		log.info("value from the autowired object {} ", comp.getValue());
//		for(String i : upperNames) {
//			log.info("uppernames EL : {}",i);
//		}
		return "component injection tested";
	}
}
