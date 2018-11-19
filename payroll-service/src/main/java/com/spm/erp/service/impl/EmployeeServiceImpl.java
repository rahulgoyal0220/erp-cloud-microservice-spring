package com.spm.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spm.erp.model.Employee;
import com.spm.erp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${employee.get.url}")
	String getURL;
	
	@Override
	public Employee fetchEmployeeById(Integer id) {
	    String modifiedURL = getURL.replace("{id}", id.toString());
		Employee e= restTemplate.getForObject(modifiedURL, Employee.class);
		return e;
	}

}
