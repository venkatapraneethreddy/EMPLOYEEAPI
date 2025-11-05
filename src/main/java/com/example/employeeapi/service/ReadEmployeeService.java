package com.example.employeeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import java.util.Optional;

@Service
public class ReadEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Employee getByName(String firstName) {
        Optional<Employee> emp = employeeRepository.findAll()
                .stream()
                .filter(e -> e.getFirstName() != null && e.getFirstName().equalsIgnoreCase(firstName))
                .findFirst();
        return emp.orElse(null);
    }
}
