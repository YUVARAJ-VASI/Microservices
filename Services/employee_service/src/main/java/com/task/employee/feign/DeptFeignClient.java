package com.task.employee.feign;

import com.task.employee.dto.DeptDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "department-service")
public interface DeptFeignClient {

    @GetMapping("/getDept/{id}")
    DeptDto findByDeptId(@PathVariable("id") long id);

    @GetMapping("/getDepts")
    List<DeptDto> getAllDepts();

    @PostMapping("/bulk/getDeptsById")
    List<DeptDto> getDeptsById(@RequestBody List<Long> deptIds);

}
