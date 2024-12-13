package com.example.springdataproection.service;

import com.example.springdataproection.model.Employee;
import com.example.springdataproection.model.EmployeeProjection;
import com.example.springdataproection.repository.DepartmentRepository;
import com.example.springdataproection.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public List<EmployeeProjection> getEmployees() {
        return employeeRepository.findAllUsingProjection();
    }

    public EmployeeProjection findEmployeeById(Long employeeId) {
        return employeeRepository.findByIdUsingProjection(employeeId);
    }

    public EmployeeProjection addNewEmployee(Employee employee) {
        Employee newEmployee = Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .build();
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return findEmployeeById(savedEmployee.getEmployeeId());
    }

    public EmployeeProjection updateEmployeeById(Employee employee) {
        Employee updatedEmployee = employeeRepository.findById(employee.getEmployeeId())
                .orElseThrow();
        updatedEmployee.setPosition(employee.getPosition());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setDepartment(employee.getDepartment());
        Employee savedEmployee = employeeRepository.save(employee);
        return findEmployeeById(savedEmployee.getEmployeeId());
    }

    public EmployeeProjection deleteEmployeeById(Long employeeId) {
        EmployeeProjection employeeProjection = employeeRepository.findByIdUsingProjection(employeeId);
        Employee deletedEmployee = employeeRepository.findById(employeeId).orElseThrow();
        employeeRepository.delete(deletedEmployee);
        return employeeProjection;
    }

}
