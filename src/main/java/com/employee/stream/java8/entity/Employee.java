package com.employee.stream.java8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

	private int employeeId;

	private String employeeName;

	private int employeeAge;

	private String employeeGender;

	private String departmentName;

	private int yearOfJoining;

	private double employeeSalary;

}
