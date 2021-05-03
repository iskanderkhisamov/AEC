package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.User;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;
import static ru.kstu.aec.configs.SecurityConfig.isTeacher;

@Controller
public class TestsController {
    @PostMapping("/done")
    public String done() {
        System.out.println("BUTTON DONE!!!");
        return "redirect:/statistics";
    }
}
