package com.task.department.dto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "emp_department")
@Data
public class DeptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
}
