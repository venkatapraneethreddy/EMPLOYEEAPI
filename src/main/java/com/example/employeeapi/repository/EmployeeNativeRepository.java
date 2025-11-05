package com.example.employeeapi.repository;

import com.example.employeeapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeNativeRepository extends JpaRepository<Employee, Long> {

    // Native SQL query to find employee by email
    @Query(value = "SELECT * FROM employee WHERE email = :email", nativeQuery = true)
    Optional<Employee> findByEmailNative(@Param("email") String email);

    // Native SQL query to find employee by name
    @Query(value = "SELECT * FROM employee WHERE first_name = :name", nativeQuery = true)
    Optional<Employee> findByNameNative(@Param("name") String name);
}
