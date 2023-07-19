package com.leesj.backend0801.repository;

import com.leesj.backend0801.entity.Student;
import com.leesj.backend0801.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public default List<Student> findAll(Pagination pagination) {
        Page<Student> page = this.findAll(
                PageRequest.of(pagination.getPg() - 1, pagination.getSz(),
                Sort.Direction.ASC,
                "id"));
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }
}
