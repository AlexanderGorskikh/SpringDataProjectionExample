package com.example.springdataproection.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "employees")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String position;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
