package com.example.controller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.controller.Models.Student;

@Controller
public class HomeController {

    @GetMapping("/Student")
    public String demoPage(Model model) {
        Student student = new Student(1, "Nguyễn Văn A");
        model.addAttribute("student", student);
        model.addAttribute("message", "Welcome Thymeleaf!");
    return "index";
}

}
