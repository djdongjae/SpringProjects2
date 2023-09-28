package net.skhu.mybatis1_2023_07_14_1035i.controller;

import net.skhu.mybatis1_2023_07_14_1035i.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("department/list")
    public String list(Model model) {
        model.addAttribute("departments", departmentMapper.findAll());
        return "department/list";
    }
}
