package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

    @GetMapping("/statistics")
    public String handleGet() {
        return "redir";
    }
    // тут мы перенаправляем юзера на реактовский фронт

}
