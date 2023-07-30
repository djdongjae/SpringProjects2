package com.dongjae.jpa2_202307302157i.repository;

import com.dongjae.jpa2_202307302157i.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
