package com.example.employeeapi.test;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import com.example.employeeapi.repository.EmployeeHQLRepository;
import com.example.employeeapi.repository.EmployeeNativeRepository;
import com.example.employeeapi.service.SearchEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeHQLRepository hqlRepository;

    @MockBean
    private EmployeeNativeRepository nativeRepository;

    @Autowired
    private SearchEmployeeService searchService;

    // --- JPA Specification tests ---
    @Test
    public void testSearchByEmailSpec() {
        Employee emp = new Employee();
        emp.setFirstName("Name");
        emp.setEmail("name@gmail.com");

        when(employeeRepository.findOne(any(Specification.class))).thenReturn(Optional.of(emp));

        Optional<Employee> result = searchService.searchByEmailSpec("name@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("name@gmail.com", result.get().getEmail());
    }

    @Test
    public void testSearchByNameSpec_NotFound() {
        when(employeeRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());
        Optional<Employee> result = searchService.searchByNameSpec("Unknown");
        assertTrue(result.isEmpty());
    }

    // --- HQL tests ---
    @Test
    public void testSearchByEmailHQL() {
        Employee emp = new Employee();
        emp.setFirstName("Name");
        emp.setEmail("name@gmail.com");

        when(hqlRepository.findByEmailHQL("name@gmail.com")).thenReturn(Optional.of(emp));

        Optional<Employee> result = searchService.searchByEmailHQL("name@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("name@gmail.com", result.get().getEmail());
    }

    @Test
    public void testSearchByNameHQL_NotFound() {
        when(hqlRepository.findByNameHQL("Ghost")).thenReturn(Optional.empty());
        Optional<Employee> result = searchService.searchByNameHQL("Ghost");
        assertTrue(result.isEmpty());
    }

    // --- Native SQL tests ---
    @Test
    public void testSearchByEmailNative() {
        Employee emp = new Employee();
        emp.setFirstName("Name");
        emp.setEmail("name@gmail.com");

        when(nativeRepository.findByEmailNative("name@gmail.com")).thenReturn(Optional.of(emp));

        Optional<Employee> result = searchService.searchByEmailNative("name@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("name@gmail.com", result.get().getEmail());
    }

    @Test
    public void testSearchByNameNative_NotFound() {
        when(nativeRepository.findByNameNative("Nobody")).thenReturn(Optional.empty());
        Optional<Employee> result = searchService.searchByNameNative("Nobody");
        assertTrue(result.isEmpty());
    }
}
