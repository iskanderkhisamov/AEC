package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        System.out.println(getAuthentication());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
