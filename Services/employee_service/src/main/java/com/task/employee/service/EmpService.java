package com.task.employee.service;

import com.task.employee.dao.repo.EmpRepository;
import com.task.employee.dto.DeptDto;
import com.task.employee.dto.EmpDto;
import com.task.employee.dto.entity.EmpEntity;
import com.task.employee.dto.mapper.EmpMapper;
import com.task.employee.feign.DeptFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmpService {

    EmpRepository empRepository;
    EmpMapper empMapper;
    DeptFeignClient deptFeignClient;

    public EmpService(EmpRepository empRepository, EmpMapper empMapper, DeptFeignClient deptFeignClient) {
        this.empRepository = empRepository;
        this.empMapper = empMapper;
        this.deptFeignClient = deptFeignClient;
    }


    public EmpDto saveEmp(EmpDto empDto) {

        log.info("saveEmp Request: {}",  empDto);
        EmpEntity empEntity;

        if (Objects.isNull(empDto.getName()))
            throw new RuntimeException("Employee name can't be NULL");
        else
            empEntity = empRepository.save(empMapper.toEntity(empDto));

        return empMapper.toDto(empEntity);
    }

    public List<EmpDto> getAllEmp() {

        log.info("getAllEmp");

        List<EmpEntity> empEntities = empRepository.findAll();

        if(!empEntities.isEmpty()){

            List<Long> deptIdList = empEntities.stream()
                    .map(EmpEntity::getDept_id)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

            Map<Long,DeptDto> deptMap = deptIdList.isEmpty() ? Collections.emptyMap()
                            :deptFeignClient.getDeptsById(deptIdList)
                    .stream()
                    .collect(
                            Collectors.toMap( DeptDto::id,Function.identity())
                    );

            return empEntities.stream()
                    .map(empEntity -> empMapper.toDto(
                            empEntity,
                            deptMap.get(empEntity.getDept_id())
                    ))
                    .toList();
        }
        else
            throw new RuntimeException("No employees found");
    }

    public EmpDto findByEmpId(long empId) {

        log.info("getEmpById Request: {}",  empId);

        EmpDto empDto = empMapper.toDto(empRepository.getReferenceById(empId));

        if(Objects.nonNull(empDto)){
            DeptDto deptDto = deptFeignClient.findByDeptId(empDto.getDeptDto().id());
            empDto.setDeptDto(deptDto);
            return empDto;
        }
        else throw new RuntimeException("No employee found for " + empId);
    }



}
