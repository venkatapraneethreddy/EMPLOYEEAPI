package com.example.employeeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.CreateEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class CreateEmployeeController {

    @Autowired
    private CreateEmployeeService createService;

    // Create Basic Employee (only firstName + email)
    @PostMapping("/create/basic")
    public ResponseEntity<Employee> createBasic(@RequestBody Employee employee) {
        Employee emp = createService.createBasicEmployee(employee.getFirstName(), employee.getEmail());
        return ResponseEntity.ok(emp);
    }

    // Create Full Employee (all fields)
    @PostMapping("/create/full")
    public ResponseEntity<Employee> createFull(@RequestBody Employee employee) {
        Employee emp = createService.createFullEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone());
        return ResponseEntity.ok(emp);
    }
}
