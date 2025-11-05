package com.example.employeeapi.test;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll();

        Employee emp = new Employee();
        emp.setFirstName("Name");
        emp.setEmail("name@gmail.com");
        employeeRepository.save(emp);
    }

    // --- Base read endpoints ---
    @Test
    public void testGetEmployeeByEmail() throws Exception {
        mockMvc.perform(get("/api/employees/email/name@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("name@gmail.com"));
    }

    @Test
    public void testGetEmployeeByName() throws Exception {
        mockMvc.perform(get("/api/employees/name/Name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Name"));
    }

    @Test
    public void testEmployeeNotFound() throws Exception {
        mockMvc.perform(get("/api/employees/email/missing@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // --- JPA Specification search ---
    @Test
    public void testSearchByEmailSpec() throws Exception {
        mockMvc.perform(get("/api/employees/search/spec/email")
                .param("email", "name@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("name@gmail.com"));
    }

    @Test
    public void testSearchByNameSpec() throws Exception {
        mockMvc.perform(get("/api/employees/search/spec/name")
                .param("name", "Name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Name"));
    }

    // --- HQL search ---
    @Test
    public void testSearchByEmailHQL() throws Exception {
        mockMvc.perform(get("/api/employees/search/hql/email")
                .param("email", "name@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchByNameHQL() throws Exception {
        mockMvc.perform(get("/api/employees/search/hql/name")
                .param("name", "Name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // --- Native SQL search ---
    @Test
    public void testSearchByEmailNative() throws Exception {
        mockMvc.perform(get("/api/employees/search/native/email")
                .param("email", "name@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchByNameNative() throws Exception {
        mockMvc.perform(get("/api/employees/search/native/name")
                .param("name", "Name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
