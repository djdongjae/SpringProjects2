package net.skhu.jpa1_202307281635i.controller;

import net.skhu.jpa1_202307281635i.entity.Department;
import net.skhu.jpa1_202307281635i.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("departments")
    public List<Department> departments() {
        return departmentRepository.findAll();
    }

    @GetMapping("department/{id}")
    public Department department(@PathVariable("id") int id) {
        return departmentRepository.findById(id).get();
    }

    @PostMapping("department")
    public boolean insert(@RequestBody Department department) {
        departmentRepository.save(department);
        return true;
    }

    @PutMapping("department")
    public boolean update(@RequestBody Department department) {
        departmentRepository.save(department);
        return true;
    }
    
    @DeleteMapping("department/{id}")
    public boolean delete(@PathVariable("id") int id) {
        departmentRepository.deleteById(id);
        return true;
    }
}
