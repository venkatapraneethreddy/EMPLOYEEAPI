package com.example.employeeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

@Service
public class UpdateEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee updateEmployee(String email, String lastName, String phone, String address) {
        Employee e = employeeRepository.findByEmail(email);
        if (e != null) {
            if (lastName != null) e.setLastName(lastName);
            if (phone != null) e.setPhone(phone);
            if (address != null) e.setAddress(address);
            return employeeRepository.save(e);
        }
        return null;
    }

    public Employee updatePhone(String email, String phone) {
        Employee e = employeeRepository.findByEmail(email);
        if (e != null) {
            e.setPhone(phone);
            return employeeRepository.save(e);
        }
        return null;
    }
}
