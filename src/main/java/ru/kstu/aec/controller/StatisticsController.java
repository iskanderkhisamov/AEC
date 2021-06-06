package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatisticsController {

    @GetMapping("/result/{id}")
    public String handleGet(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "result";
    }
}
