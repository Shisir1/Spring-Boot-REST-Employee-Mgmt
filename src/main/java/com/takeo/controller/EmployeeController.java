package com.takeo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.takeo.entity.Employee;
import com.takeo.exception.ResourceNotFoundException;
import com.takeo.repo.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;

	@GetMapping("/employees")
	public List<Employee> retriveAllUsers() {
		return empRepo.findAll();
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return empRepo.save(employee);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws ResourceNotFoundException {

		Employee employee = empRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found For this Id :: " + id));

		return ResponseEntity.ok().body(employee);
	}

	@DeleteMapping("/employee/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable int eid) throws ResourceNotFoundException {

		Employee employee = empRepo.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found For this Id :: " + eid));

		empRepo.delete(employee);

		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return response;
	}

	@PutMapping("/employees/{eid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer eid, @RequestBody Employee edetails)
			throws ResourceNotFoundException {

		Employee employee = empRepo.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found For this Id :: " + eid));

		employee.setEmail(edetails.getEmail());
		employee.setLastName(edetails.getLastName());

		final Employee updateEmployee = empRepo.save(employee);

		return ResponseEntity.ok(updateEmployee);
	}

}
