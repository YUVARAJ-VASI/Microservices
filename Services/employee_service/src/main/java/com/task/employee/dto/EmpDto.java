package com.task.employee.dto;

import lombok.Data;

@Data
public class EmpDto{

    long id;
    String name;
    String email;
    long salary;
    DeptDto deptDto;
}
