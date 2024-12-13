package com.example.springdataproection;

import com.example.springdataproection.controller.EmployeeController;
import com.example.springdataproection.model.Employee;
import com.example.springdataproection.model.EmployeeProjection;
import com.example.springdataproection.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @MockBean
    private EmployeeService companyService;

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldReturn_AllEmployees() throws Exception {
        EmployeeProjection employeeProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "Mark Ross";
            }

            @Override
            public String getPosition() {
                return "Developer";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }
        };
        when(companyService.getEmployees()).thenReturn(Collections.singletonList(employeeProjection));

        mockMvc.perform(get("/api/v1/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fullName").value("Mark Ross"))
                .andExpect(jsonPath("$[0].position").value("Developer"))
                .andExpect(jsonPath("$[0].departmentName").value("IT"));

        verify(companyService, times(1)).getEmployees();
    }

    @Test
    public void shouldReturn_EmployeeById() throws Exception {
        EmployeeProjection employeeProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "Mark Ross";
            }

            @Override
            public String getPosition() {
                return "Developer";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }
        };
        when(companyService.findEmployeeById(1L)).thenReturn(employeeProjection);

        mockMvc.perform(get("/api/v1/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Mark Ross"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(companyService, times(1)).findEmployeeById(1L);
    }

    @Test
    public void should_AddNewEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Mark");
        employee.setLastName("Ross");
        employee.setPosition("Developer");
        employee.setSalary(1000.0);

        EmployeeProjection employeeProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "Mark Ross";
            }

            @Override
            public String getPosition() {
                return "Developer";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }
        };
        when(companyService.addNewEmployee(any(Employee.class))).thenReturn(employeeProjection);

        mockMvc.perform(post("/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Mark Ross"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(companyService, times(1)).addNewEmployee(any(Employee.class));
    }

    @Test
    public void should_UpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstName("Mark");
        employee.setLastName("Ross");
        employee.setPosition("The best Developer");
        employee.setSalary(2000.0);

        EmployeeProjection employeeProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "Mark Ross";
            }

            @Override
            public String getPosition() {
                return "The best Developer";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }
        };
        when(companyService.updateEmployeeById(any(Employee.class))).thenReturn(employeeProjection);

        mockMvc.perform(put("/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Mark Ross"))
                .andExpect(jsonPath("$.position").value("The best Developer"))
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(companyService, times(1)).updateEmployeeById(any(Employee.class));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        EmployeeProjection employeeProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "Mark Ross";
            }

            @Override
            public String getPosition() {
                return "Developer";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }
        };
        when(companyService.deleteEmployeeById(1L)).thenReturn(employeeProjection);

        mockMvc.perform(delete("/api/v1/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Mark Ross"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(companyService, times(1)).deleteEmployeeById(1L);
    }


}
