package com.spm.erp.service;

import java.util.List;

import com.spm.erp.exception.CustomException;
import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<Employee> getAllEmployee(Pageable pageable);

    CustomResponse<Employee> getEmployeeDetail(Integer id);

    CustomResponse<Employee> create(Employee employee);

    CustomResponse<Employee> deleteEmployee(Integer id);

    CustomResponse<Employee> updateEmployee(Integer id, Employee employee);

    CustomResponse<Employee> updateEmployeePassword(Integer id, Employee employee);

    List<Employee> getReportees(Integer managerId);

}
