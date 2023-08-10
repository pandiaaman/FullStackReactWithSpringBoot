package com.pandiaaman.bak.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="employee_table_trial")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TrialEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="employee_id")
	private String employeeId;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name="employee_salary")
	private double employeeSalary;
	@Column(name="employee_region")
	private String employeeRegion;
	
	@Override
	public boolean equals(Object otherEmp) {
		boolean result = false;
		
		if(otherEmp instanceof TrialEmployee) {
			TrialEmployee otherEmpOb = (TrialEmployee) otherEmp;
			result = (this.employeeId == otherEmpOb.employeeId);
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return (Integer.parseInt(this.employeeId));
	}
}
