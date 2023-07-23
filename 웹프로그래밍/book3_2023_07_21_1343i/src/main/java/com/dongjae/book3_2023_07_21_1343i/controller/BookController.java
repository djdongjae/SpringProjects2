package com.dongjae.book3_2023_07_21_1343i.controller;

import com.dongjae.book3_2023_07_21_1343i.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    BookMapper bookMapper;

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("books", bookMapper.findAll());
        return "book/list";
    }

    @GetMapping("detail")
    public String detail(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if (id == null) id=5;
        model.addAttribute("book", bookMapper.findById(id));
        return "book/detail";
    }
}
