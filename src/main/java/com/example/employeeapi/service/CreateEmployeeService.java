package com.example.employeeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

@Service
public class CreateEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create employee with only firstName and email
    public Employee createBasicEmployee(String firstName, String email) {
        Employee e = new Employee();
        e.setFirstName(firstName);
        e.setEmail(email);
        return employeeRepository.save(e);
    }

    // Create employee with firstName, lastName, email, and phone
    public Employee createFullEmployee(String firstName, String lastName, String email, String phone) {
        Employee e = new Employee();
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setEmail(email);
        e.setPhone(phone);
        return employeeRepository.save(e);
    }
}
