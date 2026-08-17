package com.task.employee.dto.mapper;

import com.task.employee.dto.DeptDto;
import com.task.employee.dto.EmpDto;
import com.task.employee.dto.entity.EmpEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpMapper {

    @Mapping(source = "empEntity.dept_id", target = "deptDto.id")
    EmpDto toDto(EmpEntity  empEntity);

    @Mapping(source = "deptDto.id", target = "id")
    EmpEntity toEntity(EmpDto empDto);

    List<EmpDto> toDto(List<EmpEntity>  empEntity);
    List<EmpEntity> toEntity(List<EmpDto> empDto);

    @Mapping(source = "deptDto.id", target = "deptDto.id")
    @Mapping(source = "deptDto.name", target = "deptDto.name")
    @Mapping(source = "empEntity.id", target = "id")
    @Mapping(source = "empEntity.name", target = "name")
    EmpDto toDto(EmpEntity empEntity , DeptDto deptDto);

    @Mapping(source = "deptDto.id", target = "dept_id")
    @Mapping(source = "empDto.id", target = "id")
    @Mapping(source = "empDto.name", target = "name")
    EmpEntity toEntity(EmpDto empDto , DeptDto deptDto);

}
