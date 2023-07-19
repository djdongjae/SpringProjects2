package com.leesj.backend0801.repository;

import com.leesj.backend0801.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
