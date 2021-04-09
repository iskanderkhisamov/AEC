package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreatorController {

    @GetMapping("/create")
    public String getCreator() {
        return "creator";
    }
}
