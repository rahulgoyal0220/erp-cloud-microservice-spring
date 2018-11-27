package com.spm.erp.controller;

import com.spm.erp.client.EmployeeClient;
import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Employee;
import com.spm.erp.model.Payroll;
import com.spm.erp.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PayrollController {

	@Autowired
	PayrollService payrollService;

	@Autowired
	EmployeeClient employeeClient;

	@GetMapping("/payroll")
	public ResponseEntity<?> getAllPayrolls() {
		return ResponseEntity.ok(payrollService.fetchAllPayrolls());
	}

	@GetMapping("/payroll/{payroll_id}")
	public ResponseEntity<?> getPayrollById(@PathVariable("payroll_id") Integer id) {
		Optional<Payroll> payroll = payrollService.fetchPayrollById(id);
		return ResponseEntity.ok(payroll.isPresent() ? payroll.get()
				: new CustomResponse(Boolean.FALSE, "No Payroll found with id:" + id));
	}

	@PostMapping("/payroll")
	public ResponseEntity<?> insertPayroll(@Valid @RequestBody Payroll payroll) {
		boolean isSaved = payrollService.insertPayroll(payroll);
		CustomResponse response;
		if (isSaved) {
			response = new CustomResponse(Boolean.TRUE, "Payroll saved successfully.");
		} else {
			response = new CustomResponse(Boolean.FALSE, "Payroll not saved.");
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/payroll/{payroll_id}")
	public ResponseEntity<?> updatePayroll(@PathVariable("payroll_id") Integer id, @RequestBody Payroll payroll) {
		boolean isUpdated = payrollService.updatePayroll(payroll, id);
		CustomResponse response;
		if (isUpdated) {
			response = new CustomResponse(Boolean.TRUE, "Payroll updated successfully!");
		} else {
			response = new CustomResponse(Boolean.FALSE, "No Payroll found with id:" + id);
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/payroll/{payroll_id}")
	public ResponseEntity<?> deletePayroll(@PathVariable("payroll_id") Integer id) {
		Optional<Payroll> payroll = payrollService.fetchPayrollById(id);
		if (!payroll.isPresent()) {
			return ResponseEntity.ok(new CustomResponse(Boolean.FALSE, "No Payroll found with id:" + id));
		}
		payrollService.deletePayroll(id);
		return ResponseEntity.ok(new CustomResponse(Boolean.TRUE, "Payroll deleted successfully."));
	}

	@GetMapping("/payroll/{payroll_id}/report")
	public ResponseEntity<?> getPaySlip(@PathVariable("payroll_id") Integer id) {
		Optional<Payroll> payroll = payrollService.fetchPayrollById(id);
		if (!payroll.isPresent()) {
			return ResponseEntity.ok(new CustomResponse(Boolean.FALSE, "No Payroll found with id:" + id));
		}
		ResponseEntity<CustomResponse<Employee>> employee = employeeClient.getEmployeeById(payroll.get().getEmployeeId());
		byte[] bytes = payrollService.generatePayrollReport(payroll.get(), employee.getBody().getResponse());

		return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8")
				.header("Content-Disposition", "inline; filename=\"Payroll_" + payroll.get().getId() + ".pdf\"")
				.body(bytes);

	}
}
