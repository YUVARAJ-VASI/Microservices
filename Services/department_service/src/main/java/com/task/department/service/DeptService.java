package com.task.department.service;

import com.task.department.dao.repo.DeptRepository;
import com.task.department.dto.DeptDto;
import com.task.department.dto.entity.DeptEntity;
import com.task.department.dto.mapper.DeptMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DeptService {

    DeptRepository deptRepository;
    DeptMapper deptMapper;

    public DeptService(DeptRepository deptRepository,  DeptMapper deptMapper) {
        this.deptRepository = deptRepository;
        this.deptMapper = deptMapper;
    }


    public DeptDto saveDept(DeptDto deptDto) {

        log.info("saveEmp Request: {}",  deptDto);

        DeptEntity deptEntity;
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            log.error("saveDept : ",e);
        }
        if (Objects.isNull(deptDto.name()))
            throw new RuntimeException("Department name can't be NULL");
        else
            deptEntity = deptRepository.save(deptMapper.toEntity(deptDto));

        return deptMapper.toDto(deptEntity);
    }

    public List<DeptDto> getAllDepts() {

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            log.error("saveDept : ",e);
        }

        List<DeptDto> deptDtoList = deptMapper.toDto(deptRepository.findAll());

        if(!deptDtoList.isEmpty())
            return deptDtoList;
        else
            log.error("No Department found for getAllDepts");
        return Collections.emptyList();
    }

    public List<DeptDto> getAllDeptsById(List<Long> deptIds) {

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            log.error("saveDept : ",e);
        }

        List<DeptDto> deptDtoList = deptMapper.toDto(deptRepository.findAllById(deptIds));

        if(!deptDtoList.isEmpty())
            return deptDtoList;
        else
            log.error("No Department found for getAllDeptsById");
        return Collections.emptyList();
    }

    public DeptDto findByDeptId(long deptId) {

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            log.error("saveDept : ",e);
        }

        log.info("getDeptById Request: {}",  deptId);

        DeptDto deptDto = deptMapper.toDto(deptRepository.getReferenceById(deptId));

        if(Objects.nonNull(deptDto))
            return deptDto;
        else
            log.error("No Department found for {}", deptId);
        return null;

    }
}
