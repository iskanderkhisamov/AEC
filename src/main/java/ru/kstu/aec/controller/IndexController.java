package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        String text = "Здравствуйте Степан, добро пожаловать в Россию.";
        model.addAttribute("sText", text);
        return "index";
    }
}
