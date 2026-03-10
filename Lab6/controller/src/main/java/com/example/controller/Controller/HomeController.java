package com.example.controller.Controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class HomeController {

    // @GetMapping("/Student")
    // public String demoPage(Model model) {
    //     Student student = new Student(1, "Nguyễn Văn A");
    //     model.addAttribute("student", student);
    //     model.addAttribute("message", "Welcome Thymeleaf!");
    // return "index";
    @GetMapping("/home")
    public String getMethodName(Principal principal) {
        return "Hello " + principal.getName();
    }   
}

