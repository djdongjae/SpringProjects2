package net.skhu.mybatis1_2023_07_14_1035i.controller;

import net.skhu.mybatis1_2023_07_14_1035i.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @RequestMapping("student/list")
    public String list(Model model) {
        model.addAttribute("students", studentMapper.findAll());
        return "student/list";
    }

    @GetMapping("student/list1")
    public String list1(Model model) {
        model.addAttribute("srchText", "");
        model.addAttribute("students", studentMapper.findAll());
        return "student/list1";
    }

    @PostMapping ("student/list1")
    public String list1(Model model, String srchText) {
        if (srchText == null) srchText = "";
        model.addAttribute("students", studentMapper.findByName(srchText + "%"));
        model.addAttribute("srchText", srchText);
        return "student/list1";
    }
}
