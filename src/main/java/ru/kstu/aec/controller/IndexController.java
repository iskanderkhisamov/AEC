package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "main";
    }

    @GetMapping("/react")
    public String react() {
        return "index";
    }
}
