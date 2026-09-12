package com.task.department.controller;

import com.task.department.dto.DeptDto;
import com.task.department.service.DeptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DeptController {

    DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @PostMapping("/saveDept")
    public ResponseEntity<DeptDto> save(@RequestBody DeptDto deptDto) {
        return new ResponseEntity<>(deptService.saveDept(deptDto), HttpStatus.CREATED);
    }

    @GetMapping("/getDepts")
    public ResponseEntity<List<DeptDto>> getAllDepts() {
        return new ResponseEntity<>(deptService.getAllDepts(), HttpStatus.OK);
    }

    @PostMapping("/bulk/getDeptsById")
    public ResponseEntity<List<DeptDto>> getDeptsById(@RequestBody List<Long> deptIds) {
        return new ResponseEntity<>(deptService.getAllDeptsById(deptIds), HttpStatus.OK);
    }

    @GetMapping("/getDept/{id}")
    public ResponseEntity<DeptDto> findByDeptId(@PathVariable("id") long id) {
        return new ResponseEntity<>(deptService.findByDeptId(id), HttpStatus.OK);
    }

}
