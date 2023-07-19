package com.leesj.backend0801.controller;

import com.leesj.backend0801.entity.Student;
import com.leesj.backend0801.model.Pagination;
import com.leesj.backend0801.repository.DepartmentRepository;
import com.leesj.backend0801.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("list")
    public @ResponseBody List<Student> list(Model model, Pagination pagination) {
        List<Student> students = studentRepository.findAll(pagination);
        model.addAttribute("students", students);
        return students;
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
        model.addAttribute("student", new Student());
        model.addAttribute("departments", departmentRepository.findAll());
        return "student/edit";
    }

    @PostMapping("create")
    public String create(Model model, Student student, Pagination pagination) {
        studentRepository.save(student);
        int lastPage = (int)Math.ceil((double)studentRepository.count() / pagination.getSz());
        pagination.setPg(lastPage);
        return "redirect:list?" + pagination.getQueryString();
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id, Pagination pagination) {
        Student student = studentRepository.findById(id).get();
        model.addAttribute("student", student);
        model.addAttribute("departments", departmentRepository.findAll());
        return "student/edit";
    }

    @PostMapping(value = "edit", params = "cmd=save")
    public String edit(Model model, Student student, Pagination pagination) {
        studentRepository.save(student);
        return "redirect:list?" + pagination.getQueryString();
    }

    @PostMapping(value = "edit", params = "cmd=delete")
    public String delete(Model model, @RequestParam("id") int id, Pagination pagination) {
        studentRepository.deleteById(id);
        return "redirect:list?" + pagination.getQueryString();
    }
}
