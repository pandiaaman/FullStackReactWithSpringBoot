package com.pandiaaman.bak.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pandiaaman.bak.entities.TrialEmployee;

public interface TiralEmployeeRepo extends JpaRepository<TrialEmployee, String> {

	List<TrialEmployee> findByEmployeeRegion(String region);
	
	//we have JPQL below
	@Query("select e from TrialEmployee e where e.employeeSalary >= ?1 and e.employeeSalary <= ?2")
	List<TrialEmployee> findInSalaryRange(double from, double to);
	
	Page<TrialEmployee> findByEmployeeSalaryGreaterThan(double salary, Pageable pageable);
}
