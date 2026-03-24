package com.example.controller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.controller.Services.BookService;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        Pageable pageable = PageRequest.of(0, 8);
        var page = bookService.searchBooks(null, null, pageable);
        model.addAttribute("featured", page.getContent());
        model.addAttribute("categories", bookService.getAllCategories());
        return "home";
    }
}

