package com.luv2code.springboot.Thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.Thymeleafdemo.entity.Employee;
import com.luv2code.springboot.Thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployee(Model theModel) {

		// get employee from the db

		List<Employee> theEmployees = employeeService.findall();

		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// get employee from the db

		Employee theEmployee = new Employee();

		theModel.addAttribute("employees", theEmployee);

		return "employees/employee-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {
		//get the employee from the service
		Employee theEmployee =employeeService.findById(theId);
		
		//set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employees",theEmployee);
		//send over to our form
		

		return "employees/employee-form";

	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employees") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent dublicate submission
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.delete(theId);
		
		return "redirect:/employees/list"; 
	}
}
