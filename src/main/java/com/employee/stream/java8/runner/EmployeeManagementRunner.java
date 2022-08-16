package com.employee.stream.java8.runner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.employee.stream.java8.entity.Employee;

@Component
public class EmployeeManagementRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList.add(new Employee(110, "Sabyasachi Rajkumar", 27, "Male", "Product Development", 2022 , 85000.30));				
		employeeList.add(new Employee(111, "Dipika Rout", 23, "Female", "HR", 2022, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2022, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2020,18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2021, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2019, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2015, 10500.0));			
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2016, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 44, "Female", "Sales And Marketing", 2015, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2012, 110000.5));			
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2019, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 45, "Male", "Product Development", 2000, 682500.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2016, 21300.0));				
		employeeList.add(new Employee(244, "Nicolus Den", 34, "Male", "Sales And Marketing", 2015, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 30, "Female", "Product Development", 2016, 28900.0));				
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2017, 35700.0));

				
		employeeList.forEach(System.out::println);

		//1. Number of male and female employees are there in the organization
		Map<String, Long> numberOfMaleAndFemale
		    = employeeList.stream()
				          .collect(Collectors.groupingBy(Employee::getEmployeeGender, Collectors.counting()));
		System.out.println(numberOfMaleAndFemale);

		//2. Print the name of all departments in the organization
		System.out.println("=================================================");
		employeeList.stream()
		            .map(emp -> emp.getDepartmentName())
		            .distinct()
		            .collect(Collectors.toList())
		            .forEach(System.out::println);

		//3. What is the average age of male and female employees
		System.out.println("=================================================");
		Map<String, Double> averageAgeOfMaleAndFemale 
		    = employeeList.stream()
                          .collect(Collectors.groupingBy(Employee::getEmployeeGender, 
	                                Collectors.averagingInt(Employee::getEmployeeAge)));
		System.out.println(averageAgeOfMaleAndFemale);

		//4. Get the details of highest paid employee in the organization
		System.out.println("=================================================");
		Optional<Employee> highestPaidEmployeeWrapper
		  = employeeList.stream()
				        .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getEmployeeSalary)));
		Employee employeeWrapper = highestPaidEmployeeWrapper.get();
		System.out.println("ID : " + employeeWrapper.getEmployeeId());

		System.out.println("Name : " + employeeWrapper.getEmployeeName());
		System.out.println("Age : " + employeeWrapper.getEmployeeAge());
		System.out.println("Gender : " + employeeWrapper.getEmployeeGender());
		System.out.println("Department : " + employeeWrapper.getDepartmentName());
		System.out.println("Year Of Joining : " + employeeWrapper.getYearOfJoining());
		System.out.println("Salary : " + employeeWrapper.getEmployeeSalary());

		//5. Get the names of all employees who have joined after 2015
		System.out.println("=================================================");
		employeeList.stream()
		            .filter(employee -> employee.getYearOfJoining() > 2015 - 04 - 11)
				    .map(Employee::getEmployeeName).forEach(System.out::println);

		//6. Count the number of employees in each department
		System.out.println("=================================================");

		Map<String, Long> employeeCountByDepartmentMap
		 = employeeList.parallelStream()
				       .collect(Collectors.groupingBy(Employee::getDepartmentName, Collectors.counting()));

		employeeCountByDepartmentMap.entrySet()
		                            .stream()
		                            .forEach(employee -> {System.out.println(employee.getKey() + ":" + employee.getValue());		                                    	

		});
		//7. What is the average salary of each department
		System.out.println("=================================================");

		Map<String, Double> averageSalaryMap 
		 = employeeList.stream()
		               .collect(Collectors.groupingBy(Employee::getDepartmentName,
				                Collectors.averagingDouble(Employee::getEmployeeSalary)));
		   averageSalaryMap.entrySet()
		                   .stream()
		                   .forEach(employee -> {
			                            System.out.println(employee.getKey() + ":" + employee.getValue());

		  });

		//8. Get the details of youngest male employee in the product development department
		System.out.println("=================================================");
		Optional<Employee> youngestMaleEmplpyeeWrapper
		 = employeeList.stream()
				        .filter(e -> e.getEmployeeGender() == "Male" && e.getDepartmentName() == "Product Development")
				        .min(Comparator.comparingInt(Employee::getEmployeeAge));
		Employee youngEmployeeWrapper = youngestMaleEmplpyeeWrapper.get();

		System.out.println("ID : " + youngEmployeeWrapper.getEmployeeId());
		System.out.println("Name : " + youngEmployeeWrapper.getEmployeeName());
		System.out.println("Age : " + youngEmployeeWrapper.getEmployeeAge());
		System.out.println("Gender : " + youngEmployeeWrapper.getEmployeeGender());
		System.out.println("Department : " + youngEmployeeWrapper.getDepartmentName());
		System.out.println("Year Of Joining : " + youngEmployeeWrapper.getYearOfJoining());
		System.out.println("Salary : " + youngEmployeeWrapper.getEmployeeSalary());

		//9. Who has the most working experience in the organization
		System.out.println("================================================");
		Optional<Employee> seniorMostEmployeeWrapper = employeeList.stream()
				.sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
		Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();

		System.out.println("ID : " + seniorMostEmployee.getEmployeeId());
		System.out.println("Name : " + seniorMostEmployee.getEmployeeName());
		System.out.println("Age : " + seniorMostEmployee.getEmployeeAge());
		System.out.println("Gender : " + seniorMostEmployee.getEmployeeGender());
		System.out.println("Department : " + seniorMostEmployee.getDepartmentName());
		System.out.println("Year Of Joining : " + seniorMostEmployee.getYearOfJoining());
		System.out.println("Salary : " + seniorMostEmployee.getEmployeeSalary());
		
		//10.How many male and female employees are there in the sales and marketing team
		System.out.println("=================================================");
		 Map<String, Long> noOfMaleAndFemaleEmployees = employeeList.stream()
		             .filter(e -> e.getDepartmentName()=="Sales And Marketing")
		             .collect(Collectors.groupingBy(Employee::getEmployeeGender,Collectors.counting()));		 
		 System.out.println(noOfMaleAndFemaleEmployees);
		 
		//11.sort employee based on their joining year
		 System.out.println("=================================================");
		 List<Employee> sortedEmployeeList = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining).reversed())
		 .collect(Collectors.toList());
		 sortedEmployeeList.forEach(System.out::println);
		 
		//12.Who has the lowest salary in the organization
		 System.out.println("================================================="); 
		 Optional<Employee> lowestPaidEmployeeWrapper = employeeList.stream().min(Comparator.comparingDouble(Employee::getEmployeeSalary));
		 Employee lowstEmployeeWrapper = lowestPaidEmployeeWrapper.get();
		            
		    System.out.println("ID : " + lowstEmployeeWrapper.getEmployeeId());
			System.out.println("Name : " + lowstEmployeeWrapper.getEmployeeName());
			System.out.println("Age : " + lowstEmployeeWrapper.getEmployeeAge());
			System.out.println("Gender : " + lowstEmployeeWrapper.getEmployeeGender());
			System.out.println("Department : " + lowstEmployeeWrapper.getDepartmentName());
			System.out.println("Year Of Joining : " + lowstEmployeeWrapper.getYearOfJoining());
			System.out.println("Salary : " + lowstEmployeeWrapper.getEmployeeSalary());
			
		//13.Highest paid employee in the organisation
		System.out.println("================================================="); 	
		Optional<Employee> highestPaidEmployeeWrapper1
		 = employeeList.stream()
				       .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getEmployeeSalary)));
		Employee highestPaidEmployee = highestPaidEmployeeWrapper1.get();
		System.out.println(highestPaidEmployee);
		
		//14.Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years
		System.out.println("================================================="); 	
		Map<Boolean, List<Employee>> partitionEmployeesByAgeMap
		 = employeeList.stream()
				       .collect(Collectors.partitioningBy(e -> e.getEmployeeAge() > 25));
		Set<Entry<Boolean, List<Employee>>> entrySet = partitionEmployeesByAgeMap.entrySet();
		
		for(Entry<Boolean, List<Employee>> entry : entrySet) {
			
			if(entry.getKey()) {
				System.out.println("Employees are older than 25 years :");
			}else {
				System.out.println("Employees younger than or equal to 25 years :");
			}
			System.out.println("---------------------------------");
			List<Employee> list = entry.getValue();
			list.stream()
			    .map(Employee::getEmployeeName)
			    .forEach(System.out::println);
		}
		
		
		
	}
}
