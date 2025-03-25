package com.sb.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.crud.exception.ResourceNotFoundException;
import com.sb.crud.model.Employee;
import com.sb.crud.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")	
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees findall
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll(); 
	}
	// create employee rest api )//add
	
	@PostMapping("/employees")
	public Employee addEmployee( @RequestBody Employee employee) {
		System.out.println("employee saved sucessfully");
		return employeeRepository.save(employee);
		
	}
	// fing by Id api
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>  findById(@PathVariable long id){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not exists with Id:"+id));
		return ResponseEntity.ok(employee);
	}
	
	// update Employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
		Employee employ = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not found with Id:"+id));
		employ.setFirstName(employee.getFirstName());
		employ.setLastName(employee.getFirstName());
		employ.setEmail(employee.getEmail());
		
		Employee empl = employeeRepository.save(employ);
		System.out.println("employee saved sucessfully");
		
		return ResponseEntity.ok(empl);
	}


}
