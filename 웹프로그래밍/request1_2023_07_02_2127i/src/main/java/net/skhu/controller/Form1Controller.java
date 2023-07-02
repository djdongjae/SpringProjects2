package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Form1Controller {

    @GetMapping("form1/text1")
    public String text1(Model model) {
        return "form1/text1";
    }

    @PostMapping("form1/text1")
    public String text1(Model model, String param1, String param2) {
        System.out.println(param1);
        System.out.println(param2);
        return "form1/text1";
    }

}
