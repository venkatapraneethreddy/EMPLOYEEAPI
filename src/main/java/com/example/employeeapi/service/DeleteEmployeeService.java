package com.example.employeeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeapi.repository.EmployeeRepository;

@Service
public class DeleteEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional 
    public boolean deleteByEmail(String email) {
        if (employeeRepository.existsByEmail(email)) {
            employeeRepository.deleteByEmail(email);
            return true;
        }
        return false;
    }
}
