package com.dongjae.jpa2_202307302157i.controller;

import com.dongjae.jpa2_202307302157i.entity.Department;
import com.dongjae.jpa2_202307302157i.entity.Student;
import com.dongjae.jpa2_202307302157i.repository.DepartmentRepository;
import com.dongjae.jpa2_202307302157i.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("list")
    public String list(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "student/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        Student student = new Student();
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("student", student);
        model.addAttribute("departments", departments);
        return "student/edit";
    }

    @PostMapping("create")
    public String create(Model model, Student student) {
        studentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id) {
        Student student = studentRepository.findById(id).get();
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("student", student);
        model.addAttribute("departments", departments);
        return "student/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, Student student) {
        studentRepository.save(student);
        return "redirect:list";
    }

    @RequestMapping("delete")
    public String delete(Model model, @RequestParam("id") int id) {
        studentRepository.deleteById(id);
        return "redirect:list";
    }
}
