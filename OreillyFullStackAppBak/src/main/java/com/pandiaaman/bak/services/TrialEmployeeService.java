package com.pandiaaman.bak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandiaaman.bak.entities.TrialEmployee;
import com.pandiaaman.bak.repositories.TiralEmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrialEmployeeService {

	@Autowired
	private TiralEmployeeRepo repo;
	
	public void queryEntities() {
		try {
			long empCount = repo.count();
			log.info("count of records in table :: {} ", empCount);
		}catch(Exception e) {
			log.error("error in the query entities {} ", e);
			e.printStackTrace();
		}
	}
	
	public void useStandardRepoMethods() {
		TrialEmployee emp = new TrialEmployee("asdfhnn","ananya",1000,"Mumbai");
		emp = repo.save(emp);
		log.info("id of newly added employee :: {} ",emp.getEmployeeId());
		
		//getall employees count
		log.info("employee count now :: {} ", repo.count());
		
		//display all employees
		List<TrialEmployee> employees = repo.findAll();
		for(TrialEmployee e :  employees) {
			log.info("employee detail : {}",e);
		}
		
	}
	
	public void useCustomQueryMethods() {
		//get all employees for region
		List<TrialEmployee> employeesKota = repo.findByEmployeeRegion("Kota");
		for(TrialEmployee e :  employeesKota) {
			log.info("employee FROM KOTA : {}",e);
		}
		
		
		List<TrialEmployee> employeesSalaryRange = repo.findInSalaryRange(500, 2000);
		for(TrialEmployee e :  employeesSalaryRange) {
			log.info("employee in salary range 500 to 2000 : {}",e);
		}
	}
}
