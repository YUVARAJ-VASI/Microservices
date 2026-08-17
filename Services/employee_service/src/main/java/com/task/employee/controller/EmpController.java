package com.task.employee.controller;

import com.task.employee.dto.EmpDto;
import com.task.employee.service.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {

    EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }


    @PostMapping("/saveEmp")
    public ResponseEntity<EmpDto> saveEmp(@RequestBody EmpDto empDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empService.saveEmp(empDto));
    }


    @GetMapping("/getAllEmp")
    public ResponseEntity<List<EmpDto>> getAllEmp() {
        return new ResponseEntity<>(empService.getAllEmp(), HttpStatus.OK);
    }

    @GetMapping("/getEmp/{id}")
    public ResponseEntity<EmpDto> findByEmpId(@PathVariable("id") long id) {
        return new ResponseEntity<>(empService.findByEmpId(id), HttpStatus.OK);
    }

}
