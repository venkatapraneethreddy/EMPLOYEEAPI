package com.example.employeeapi.service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import com.example.employeeapi.repository.EmployeeHQLRepository;
import com.example.employeeapi.repository.EmployeeNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeHQLRepository hqlRepo;

    @Autowired
    private EmployeeNativeRepository nativeRepo;

    // --- JPA Specification ---
    public Optional<Employee> searchByEmailSpec(String email) {
        return employeeRepository.findOne((Specification<Employee>) (root, query, cb) ->
                cb.equal(root.get("email"), email));
    }

    public Optional<Employee> searchByNameSpec(String name) {
        return employeeRepository.findOne((Specification<Employee>) (root, query, cb) ->
                cb.equal(root.get("firstName"), name));
    }

    // --- HQL ---
    public Optional<Employee> searchByEmailHQL(String email) {
        return hqlRepo.findByEmailHQL(email);
    }

    public Optional<Employee> searchByNameHQL(String name) {
        return hqlRepo.findByNameHQL(name);
    }

    // --- Native SQL ---
    public Optional<Employee> searchByEmailNative(String email) {
        return nativeRepo.findByEmailNative(email);
    }

    public Optional<Employee> searchByNameNative(String name) {
        return nativeRepo.findByNameNative(name);
    }
}
