package com.dongjae.jpa2_202307302157i.repository;

import com.dongjae.jpa2_202307302157i.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
