package com.pandiaaman.bak.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("info")
@Slf4j
@Lazy
public class SpringELinfo {
//to demonstrate spring expression language
	
	public List<String> getNames() {
		log.info("getting names list");
		
		List<String> names = new ArrayList<String>();
		
		names.add("aman");
		names.add("anisha");
		names.add("bhuvan");
		names.add("ayaz");
		names.add("sanchit");
		names.add("suhani");
		names.add("akriti");
		names.add("amit");
		names.add("bhanu");
		
		return names;
	}
	
	
	public Map<String, Integer> getMarks(){
		log.info("getting marks map");
		
		Map<String, Integer> marks = new HashMap<String, Integer>();
		
		marks.put("aman",20);
		marks.put("anisha", 30);
		marks.put("amit", 50);
		marks.put("ayaz", 40);
		marks.put("sanchit", 45);
		
		return marks;
		
	}
}
