package com.pandiaaman.bak.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.pandiaaman.bak.components.TrialObjectForConf;

@Configuration
public class TrialConfiguration {

	//below beans are singleton and are eagerly created on startup
	
	//by default bean name is same as the method name
	@Bean
	TrialObjectForConf trialOb() {
		TrialObjectForConf ob = new TrialObjectForConf();
		ob.setObvalOne(1);
		ob.setObvalTwo("starting");
		
		return ob;
	}
	
	//we can give names to beans as well
	@Bean(name="otherConfBean")
	TrialObjectForConf trialObTwo() {
		TrialObjectForConf ob = new TrialObjectForConf();
		ob.setObvalOne(2);
		ob.setObvalTwo("starting two");
		
		return ob;
	}
	
	@Bean(name="lazyConfBean")
	@Lazy
	TrialObjectForConf trialObLazy() {
		TrialObjectForConf ob = new TrialObjectForConf();
		ob.setObvalOne(3);
		ob.setObvalTwo("starting lazy");
		
		return ob;
	}
	
	@Bean(name="lazyConfBean")
	@Scope("prototype")
	TrialObjectForConf trialObPrototype() {
		TrialObjectForConf ob = new TrialObjectForConf();
		ob.setObvalOne(4);
		ob.setObvalTwo("starting prototype");
		
		return ob;
	}
}
