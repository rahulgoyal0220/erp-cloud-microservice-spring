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
			return new CustomResponse(Boolean.TRUE, "Employee details are displayed", employeeOptional.get());
		} else {
			return new CustomResponse(Boolean.FALSE, "Employee with Id# " + id + " does not exist.", null);
		}

	}

	@Override
	public CustomResponse<Employee> create(Employee employee, List<Employee> employees) {
		if (Util.validateEmail(employee) && Util.checkDuplicateEmail(employee, employees)) {
			employee.setPassword(Util.createPassword());
			employeeRepository.save(employee);
			Util.sendEmail(employee, "Account created",
					"Hello " + employee.getFirstName()
							+ " Welcome !. Your account have been created and your default password is "
							+ employee.getPassword());
			return new CustomResponse(Boolean.TRUE, "Employee saved successfully.", employee);
		} else {
			return new CustomResponse(Boolean.FALSE, "Some trouble adding the employee. Duplicate Email.", null);
		}

	}

	@Override
	public CustomResponse<Employee> deleteEmployee(Integer id) {
		try {
			employeeRepository.deleteById(id);
			return new CustomResponse(Boolean.TRUE, "Employee have been successfully deleted.", null);
		} catch (Exception e) {
			return new CustomResponse(Boolean.FALSE, "Employee have not been deleted. There were problems encountered.",
					null);
		}
	}

	@Override
	public CustomResponse<Employee> updateEmployee(Integer id, Employee employee, List<Employee> employees) {
		if (Util.checkDuplicateEmail(employee, employees)) {
			try {
				Employee emp = employeeRepository.findById(id).get();
				emp = Util.updateProperties(emp, employee);
				emp.setId(id);
				employeeRepository.save(emp);
				return new CustomResponse(Boolean.TRUE, "Employee have been successfully updated.", null);
			} catch (Exception e) {

				return new CustomResponse(Boolean.FALSE,
						"System could not update employee details. Some error occurred.", null);
			}
		} else {
			return new CustomResponse(Boolean.FALSE, "Cannot update the employee. Email is duplicate.", null);
		}

	}

	@Override
	public CustomResponse<Employee> updateEmployeePassword(Integer id, Employee employee) {
		try {
			Employee emp = employeeRepository.findById(id).get();
			emp = Util.updateProperties(emp, employee);
			emp.setId(id);
			employeeRepository.save(emp);
			Util.sendEmail(emp, "Password Update",
					"Hello " + emp.getFirstName() + " .Your password has been successfully updated");
			return new CustomResponse(Boolean.TRUE, "Employee Password have been successfully updated.", null);
		} catch (Exception e) {
			return new CustomResponse(Boolean.FALSE, "Prolems updating employee with id. ", null);
		}
	}

	@Override
	public List<Employee> getReportees(Integer managerId) {
		return employeeRepository.getEmployeeByManagerId(managerId);
	}

}
