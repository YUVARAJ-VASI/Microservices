package com.task.employee.dto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "emp_employee")
@Data
public class EmpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long salary;
    private Long dept_id;
}
