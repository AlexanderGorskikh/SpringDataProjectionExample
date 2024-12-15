package com.example.springdataproection.controller;


import com.example.springdataproection.model.Employee;
import com.example.springdataproection.model.EmployeeProjection;
import com.example.springdataproection.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @RequestMapping("home")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProjection>> getAllCompanyEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeProjection> getEmployeeById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @PostMapping
    public ResponseEntity<EmployeeProjection> addNewEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addNewEmployee(employee));
    }

    @PutMapping
    public ResponseEntity<EmployeeProjection> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employee));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeProjection> deleteEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
    }

}
