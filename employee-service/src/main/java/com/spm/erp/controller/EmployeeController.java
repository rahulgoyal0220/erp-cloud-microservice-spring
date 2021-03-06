package com.spm.erp.controller;

import com.spm.erp.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spm.erp.model.Employee;
import com.spm.erp.repository.EmployeeRepository;
import com.spm.erp.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;
    @Autowired
    EmployeeService service;

    @GetMapping
    public Page<Employee> getEmployeeList(Pageable pageable) {
        return service.getAllEmployee(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable Integer id) {
        CustomResponse response = service.getEmployeeDetail(id);
        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @GetMapping("/{id}/reportee")
    public ResponseEntity<?> getEmployeeReportee(@PathVariable Integer id) {
        List<Employee> response = service.getReportees(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    private ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {
        CustomResponse response = service.create(employee);
        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {

        CustomResponse response = service.updateEmployee(id, employee);
        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}/changePassword")
    private ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody Employee employee) {

        CustomResponse response = service.updateEmployeePassword(id, employee);
        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {

        CustomResponse response = service.deleteEmployee(id);
        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
