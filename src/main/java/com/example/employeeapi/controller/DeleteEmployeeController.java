package com.example.employeeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeeapi.service.DeleteEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class DeleteEmployeeController {

    @Autowired
    private DeleteEmployeeService deleteService;

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String email) {
        boolean deleted = deleteService.deleteByEmail(email);
        return deleted ? ResponseEntity.ok("Employee deleted successfully.")
                       : ResponseEntity.notFound().build();
    }
}
