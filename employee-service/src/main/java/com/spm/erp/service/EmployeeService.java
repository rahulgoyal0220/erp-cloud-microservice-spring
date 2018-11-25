package com.spm.erp.service;

import java.util.List;

import com.spm.erp.exception.CustomException;
import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    CustomResponse<Employee> getEmployeeDetail(Integer id);

    CustomResponse<Employee> create(Employee employee, List<Employee> employees);

    CustomResponse<Employee> deleteEmployee(Integer id);

    CustomResponse<Employee> updateEmployee(Integer id, Employee employee, List<Employee> employees);

    CustomResponse<Employee> updateEmployeePassword(Integer id, Employee employee);

    List<Employee> getReportees(Integer managerId);

}
