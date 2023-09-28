package net.skhu.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class Form4Controller {

    @GetMapping("form4/login1")
    public String login1(Model model) {
        model.addAttribute("now", new Date());
        return "form4/login1";
    }

    @PostMapping("form4/login1")
    public String login1(Model model, String userId, String password, Boolean autologin) {
        model.addAttribute("now", new Date());
        model.addAttribute("userId", userId);
        model.addAttribute("password", password);
        model.addAttribute("autologin", autologin);
        return "form4/login1";
    }

    @GetMapping("form4/login2")
    public String login2(Model model) {
        model.addAttribute("now", new Date());
        return "form4/login2";
    }

    @PostMapping("form4/login2")
    public String login2(Model model, String userId, String password, Boolean autologin) {
        model.addAttribute("now", new Date());
        model.addAttribute("userId", userId);
        model.addAttribute("autologin", autologin);
        model.addAttribute("password", password);
        return "form4/login2";
    }

    @GetMapping("form4/login3")
    public String login3(Model model) {
        model.addAttribute("now", new Date());
        return "form4/login3";
    }

    @PostMapping("form4/login3")
    public String login3(Model model, String userId, String password, Boolean autologin, HttpSession session) {
        String errorMsg = null;
        if(userId == null || userId.length() == 0)
            errorMsg = "사용자 아이디를 입력하세요";
        else if (password == null || password.length() == 0)
            errorMsg = "비밀번호를 입력하세요";
        else if (userId.equals(password) == false)
            errorMsg = "비밀번호 불일치";
        else {
            session.setAttribute("userid", userId);
            session.setAttribute("autologin", autologin);
            return "redirect:login_success";
        }
        model.addAttribute("now", new Date());
        model.addAttribute("userid", userId);
        model.addAttribute("password", password);
        model.addAttribute("autologin", autologin);
        model.addAttribute("errorMsg", errorMsg);
        return "form4/login3";
    }

    @GetMapping("form4/login_success")
    public String login_success(Model model, HttpSession session) {
        String userId = (String)session.getAttribute("userid");
        Boolean autologin = (Boolean)session.getAttribute("autologin");
        System.out.print(userId);
        System.out.println(autologin);
        return "form4/login_success";
    }

}
