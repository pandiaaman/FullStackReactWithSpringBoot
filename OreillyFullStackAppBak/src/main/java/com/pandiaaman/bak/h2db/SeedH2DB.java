package com.pandiaaman.bak.h2db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class SeedH2DB {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void init() {
//		jdbcTemplate.update("insert into employee_table_trial(employee_id, employee_name, employee_salary, employee_region) values(?,?,?,?)",new Object[] {"asdf345r","aman",5245,"Kota"});
//		jdbcTemplate.update("insert into employee_table_trial(employee_id, employee_name, employee_salary, employee_region) values(?,?,?,?)",new Object[] {"asdf3asd45r","anisha",5245,"Udaipur"});
//		jdbcTemplate.update("insert into employee_table_trial(employee_id, employee_name, employee_salary, employee_region) values(?,?,?,?)",new Object[] {"asdfwe345r","ayaz",5245,"Delhi"});
//		jdbcTemplate.update("insert into employee_table_trial(employee_id, employee_name, employee_salary, employee_region) values(?,?,?,?)",new Object[] {"asd23f345r","sanchit",5245,"Alwar"});
//		jdbcTemplate.update("insert into employee_table_trial(employee_id, employee_name, employee_salary, employee_region) values(?,?,?,?)",new Object[] {"asd235gf345r","deepak",5245,"Bangalore"});
	}
	
	@PreDestroy
	public void cleanup() {
		
	}
}
