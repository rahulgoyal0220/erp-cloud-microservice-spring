package com.spm.erp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;

@Getter
public class PayrollReport {

	private Integer id;

	private Integer employeeId;

	private Double hoursWorked;

	private Double ratePerHour;

	private Double deductions;

	private Double allowances;

	private Double netPay;

	private Double grossPay;

	private Double tax;

	private String startDate;

	private String endDate;

	private String employeefirstName;

	private String employeelastName;
	
	private String email;
	
	private PayrollReport(Integer id, Integer employeeId, Double hoursWorked, Double ratePerHour, Double deductions,
			Double allowances, Double netPay, Double grossPay, Double tax, String startDate, String endDate,
			String employeefirstName, String employeelastName, String email) {
		this.id = id;
		this.employeeId = employeeId;
		this.hoursWorked = hoursWorked;
		this.ratePerHour = ratePerHour;
		this.deductions = deductions;
		this.allowances = allowances;
		this.netPay = netPay;
		this.grossPay = grossPay;
		this.tax = tax;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employeefirstName = employeefirstName;
		this.employeelastName = employeelastName;
		this.email = email;
	}

	public static class Builder {

		private Integer id;

		private Integer employeeId;

		private Double hoursWorked;

		private Double ratePerHour;

		private Double deductions;

		private Double allowances;

		private Double netPay;

		private Double grossPay;

		private Double tax;

		private String startDate;

		private String endDate;

		private String employeefirstName;

		private String employeelastName;
		
		private String email;

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
			return this;
		}

		public Builder setHoursWorked(Double hoursWorked) {
			this.hoursWorked = hoursWorked;
			return this;
		}

		public Builder setRatePerHour(Double ratePerHour) {
			this.ratePerHour = ratePerHour;
			return this;
		}

		public Builder setDeductions(Double deductions) {
			this.deductions = deductions;
			return this;
		}

		public Builder setAllowances(Double allowances) {
			this.allowances = allowances;
			return this;
		}

		public Builder setNetPay(Double netPay) {
			this.netPay = netPay;
			return this;
		}

		public Builder setGrossPay(Double grossPay) {
			this.grossPay = grossPay;
			return this;
		}

		public Builder setTax(Double tax) {
			this.tax = tax;
			return this;
		}

		public Builder setStartDate(Date startDate) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			this.startDate = formatter.format(startDate);
			return this;
		}

		public Builder setEndDate(Date endDate) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			this.endDate = formatter.format(endDate);
			return this;
		}

		public Builder setEmployeefirstName(String employeefirstName) {
			this.employeefirstName = employeefirstName;
			return this;
		}

		public Builder setEmployeelastName(String employeelastName) {
			this.employeelastName = employeelastName;
			return this;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public PayrollReport build() {
			return new PayrollReport(id, employeeId, hoursWorked, ratePerHour, deductions, allowances, netPay, grossPay,
					tax, startDate, endDate, employeefirstName, employeelastName,email);
		}
	}

}
