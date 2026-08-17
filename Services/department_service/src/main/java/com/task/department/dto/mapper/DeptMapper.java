package com.task.department.dto.mapper;

import com.task.department.dto.DeptDto;
import com.task.department.dto.entity.DeptEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeptMapper {

    DeptDto toDto(DeptEntity deptEntity);
    DeptEntity toEntity(DeptDto deptDto);

    List<DeptDto> toDto(List<DeptEntity> deptEntityList);
    List<DeptEntity> toEntity(List<DeptDto> deptDtoList);
}
