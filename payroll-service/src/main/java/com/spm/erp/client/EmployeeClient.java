package com.spm.erp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Employee;

@FeignClient("employee-service")
public interface EmployeeClient {
	
  @GetMapping("/employee/{id}")
  ResponseEntity<CustomResponse<Employee>> getEmployeeById(@PathVariable("id") Integer employeeID);
}
