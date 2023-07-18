package net.skhu.controller;

import jakarta.servlet.http.HttpSession;
import net.skhu.dto.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Homework2Controller {

    @GetMapping("homework2/register")
    public String register(Model model) {
        return "homework2/register";
    }

    @PostMapping("homework2/register")
    public String register(Model model, HttpSession session, Student student) {
        String errorMsg = null;
        if (student.getStudentNo() == null || student.getStudentNo().length() == 0)
            errorMsg = "학번을 입력하세요";
        else if (student.getName() == null || student.getName().length() == 0)
            errorMsg = "이름을 입력하세요";
        else if (student.getEmail() == null || student.getEmail().length() == 0)
            errorMsg = "이메일을 입력하세요";
        else if (student.getDepartmentId() == 0)
            errorMsg = "전공을 선택하세요";
        else {
            session.setAttribute("student", student);
            return "redirect:register_success";
        }
        model.addAttribute("student", student);
        model.addAttribute("errorMsg", errorMsg);
        return "homework2/register";
    }

    @GetMapping("homework2/register_success")
    public String register_success(Model model) {
        return "homework2/register_success";
    }

}
