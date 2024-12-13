package com.example.springdataproection.repository;

import com.example.springdataproection.model.Employee;
import com.example.springdataproection.model.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT CONCAT(e.firstName, ' ', e.lastName) as fullName, e.position as position, " +
            "d.departmentName as departmentName FROM employees e JOIN e.department d")
    List<EmployeeProjection> findAllUsingProjection();

    @Query("SELECT CONCAT(e.firstName, ' ', e.lastName) as fullName, e.position as position, d.departmentName " +
            "as departmentName FROM employees e JOIN e.department d WHERE e.employeeId = :employeeId")
    EmployeeProjection findByIdUsingProjection(@Param("employeeId") Long employeeId);
}
