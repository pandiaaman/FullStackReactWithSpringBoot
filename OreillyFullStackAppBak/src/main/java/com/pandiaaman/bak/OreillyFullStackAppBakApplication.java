package com.pandiaaman.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pandiaaman.bak.components.TrialCheckComponent;
import com.pandiaaman.bak.components.TrialObjectForConf;
import com.pandiaaman.bak.components.TrialPrototypeComponent;
import com.pandiaaman.bak.services.TrialEmployeeService;

import lombok.extern.slf4j.Slf4j;

/*
 * @springBootApplication contains three annotations : 
 * 	@Configuration
 * 	@ComponentScan
 * 	@EnableConfiguration
 */
@SpringBootApplication(scanBasePackages = {"com.pandiaaman.bak"})
@Slf4j
public class OreillyFullStackAppBakApplication {

	public static void main(String[] args) {
		//spring on startup (run method) looks through all packages and checks for classes with @component/service/controller
		//if found, it stores them in the ApplicationContext object (context)
		ApplicationContext context = SpringApplication.run(OreillyFullStackAppBakApplication.class, args);
		
		//singleton beans : only instantiated once
		TrialCheckComponent myComponent = context.getBean("testComponent",TrialCheckComponent.class);
		TrialCheckComponent myComponent1 = context.getBean("testComponent",TrialCheckComponent.class);
		//above two gives us two references to the same bean, since singleton
		boolean areSameBeans = (myComponent == myComponent1);
		log.info("spring returns two references for same beans, if singleton :: {} ", areSameBeans);
		
		//prototype beans components : new object every time we call them
		TrialPrototypeComponent protoComponent = context.getBean("protoComp",TrialPrototypeComponent.class);
		TrialPrototypeComponent protoComponent1 = context.getBean("protoComp",TrialPrototypeComponent.class);
		TrialPrototypeComponent protoComponent2 = context.getBean("protoComp",TrialPrototypeComponent.class);
		
		
		//getting object beans from configuration files
		TrialObjectForConf ob = context.getBean("trialOb",TrialObjectForConf.class);
		
		TrialEmployeeService service = context.getBean(TrialEmployeeService.class);
		
		log.info("*************************************************");
		log.info("bak app started");
		log.info(myComponent.toString());
		log.info("value from configration file : {} and {}", ob.getObvalOne(), ob.getObvalTwo());
//		log.info(myComponent1.toString()); //called here but notice the counter on creation is only called once, means the object is only called once
		service.queryEntities();
		service.useStandardRepoMethods();
		service.useCustomQueryMethods();
		log.info("*************************************************");
	}

}
