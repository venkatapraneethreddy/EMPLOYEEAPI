package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.SearchEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees/search")
public class ReadEmployeeController {

    @Autowired
    private SearchEmployeeService searchService;

    // Search using JPA Specification
    @GetMapping("/spec")
    public ResponseEntity<Employee> searchBySpec(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name) {

        Optional<Employee> emp = (email != null)
                ? searchService.searchByEmailSpec(email)
                : searchService.searchByNameSpec(name);

        return emp.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Search using HQL
    @GetMapping("/hql")
    public ResponseEntity<Employee> searchByHQL(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name) {

        Optional<Employee> emp = (email != null)
                ? searchService.searchByEmailHQL(email)
                : searchService.searchByNameHQL(name);

        return emp.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Search using Native SQL
    @GetMapping("/native")
    public ResponseEntity<Employee> searchByNative(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name) {

        Optional<Employee> emp = (email != null)
                ? searchService.searchByEmailNative(email)
                : searchService.searchByNameNative(name);

        return emp.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
}
