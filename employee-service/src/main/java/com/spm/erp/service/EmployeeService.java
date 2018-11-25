package com.spm.erp.service;

import java.util.List;

import com.spm.erp.exception.CustomException;
import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    CustomResponse<Employee> getEmployeeDetail(Integer id);

    void create(Employee employee);

    void deleteEmployee(Integer id);

    void updateEmployee(Integer id, Employee employee);

    void updateEmployeePassword(Integer id, Employee employee);

    List<Employee> getReportees(Integer managerId);

}
