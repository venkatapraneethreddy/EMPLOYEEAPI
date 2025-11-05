package com.example.employeeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.UpdateEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class UpdateEmployeeController {

    @Autowired
    private UpdateEmployeeService updateService;

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestParam String email,
                                                   @RequestParam(required = false) String lastName,
                                                   @RequestParam(required = false) String phone,
                                                   @RequestParam(required = false) String address) {
        Employee emp = updateService.updateEmployee(email, lastName, phone, address);
        return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/update/phone")
    public ResponseEntity<Employee> updatePhone(@RequestParam String email, @RequestParam String phone) {
        Employee emp = updateService.updatePhone(email, phone);
        return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
    }
}
