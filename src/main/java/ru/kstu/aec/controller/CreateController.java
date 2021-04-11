package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateController {

    @GetMapping("/create")
    public String getCreate() {
        return "create";
    }
}
