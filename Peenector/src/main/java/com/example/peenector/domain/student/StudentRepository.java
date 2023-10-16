package com.example.peenector.domain.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findBySchoolNumber(String SchoolNumber);
    List<Student> findAllByTeamId(Long teamId);
}
