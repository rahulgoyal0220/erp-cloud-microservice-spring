package com.spm.erp.service.impl;

import com.spm.erp.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spm.erp.exception.CustomException;
import com.spm.erp.model.Employee;
import com.spm.erp.repository.EmployeeRepository;
import com.spm.erp.service.EmployeeService;
import com.spm.erp.utility.Util;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.findAll();
	}

	@Override
	public CustomResponse<Employee> getEmployeeDetail(Integer id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent()) {
			return new CustomResponse<>(true, "", employeeOptional.get());
		} else {
			return new CustomResponse(false, "Employee with Id# " + id + " does not exit.", null);
		}

	}

	@Override
	public void create(Employee employee) {
		if (Util.validateEmail(employee)) {
			employee.setPassword(passwordEncoder.encode(Util.createPassword()));
			employeeRepository.save(employee);
			Util.sendEmail(employee, "Account created",
					"Hello " + employee.getFirstName()
							+ " Welcome !. Your account have been created and your default password is "
							+ employee.getPassword());
		} else {
			System.out.println(new CustomException("Please enter a valid email id "));
		}

	}

	@Override
	public void deleteEmployee(Integer id) {
		try {
			employeeRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println(new CustomException("Problem deleting employee with id : " + id));
		}
	}

	@Override
	public void updateEmployee(Integer id, Employee employee) {
		try {
			Employee emp = employeeRepository.findById(id).get();
			emp = Util.updateProperties(emp, employee);
			emp.setId(id);
			employeeRepository.save(emp);
		} catch (Exception e) {
			System.out.println(new CustomException("Prolems updating employee with id " + id));
		}
	}

	@Override
	public void updateEmployeePassword(Integer id, Employee employee) {
		try {
			Employee emp = employeeRepository.findById(id).get();
			emp = Util.updateProperties(emp, employee);
			emp.setId(id);
			employeeRepository.save(emp);
			Util.sendEmail(emp, "Password Update",
					"Hello " + emp.getFirstName() + " .Your password has been successfully updated");
		} catch (Exception e) {
			System.out.println(new CustomException("Prolems updating employee with id " + id));
		}

	}

}
