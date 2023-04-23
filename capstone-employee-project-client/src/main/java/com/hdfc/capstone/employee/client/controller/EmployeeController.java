package com.hdfc.capstone.employee.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hdfc.capstone.employee.client.dto.EmployeeDTO;
import com.hdfc.capstone.employee.client.exception.InvalidEmployeeIdException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	RestTemplate restTemplate;
	
	String baseUrl="https://localhost:8080/employee";
	

	
	@GetMapping("/getById/{employeeId}")
	public ResponseEntity<EmployeeDTO> getById(@PathVariable long employeeId) throws InvalidEmployeeIdException{
		try {
		ResponseEntity<EmployeeDTO> response = restTemplate.getForEntity(baseUrl+"/getById/"+employeeId, EmployeeDTO.class);

		return response;
	}catch (HttpClientErrorException.BadRequest ex) {
		
		throw new InvalidEmployeeIdException();
	}
	
	}
}
