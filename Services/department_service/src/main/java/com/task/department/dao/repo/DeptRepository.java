package com.task.department.dao.repo;

import com.task.department.dto.entity.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<DeptEntity,Long> {
}
