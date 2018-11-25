package com.spm.erp.controller;

import com.spm.erp.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Employee> getEmployeeList() {
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable Integer id) {
        CustomResponse response = service.getEmployeeDetail(id);

        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/{id}/reportee")
    public ResponseEntity<?> getEmployeeReportee(@PathVariable Integer id) {
        CustomResponse response = service.getEmployeeDetail(id);

        if (response.getSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping
    private ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee employee) {
        try {
            service.create(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            service.updateEmployee(id, employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/changePassword")
    private ResponseEntity<Void> changePassword(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            service.updateEmployeePassword(id, employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        try {
            service.deleteEmployee(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
