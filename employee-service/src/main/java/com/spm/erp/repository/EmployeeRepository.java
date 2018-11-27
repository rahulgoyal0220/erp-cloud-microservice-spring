package com.spm.erp.repository;

import com.spm.erp.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    List<Employee> getEmployeeByManagerId(Integer managerId);


}
