package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestsController {

    @GetMapping("/tests")
    public String tests() {
        return "tests";
    }

    @PostMapping("/done")
    public String done() {
        System.out.println("BUTTON DONE!!!");
        return "redirect:/statistics";
    }
}
