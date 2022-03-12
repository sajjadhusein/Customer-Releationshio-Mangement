package com.luv2code.springboot.Thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.Thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	
	// that's it ..... no need ro write any code
	// all crud method will be provided by spring data JPA
	
	//add a method to sort by lastname
	
	
	//spring JPA will read the method name and add the code for sort by last Name//magic
	public List<Employee> findAllByOrderByLastNameAsc();
}
