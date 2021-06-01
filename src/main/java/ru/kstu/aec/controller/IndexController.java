package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.services.TestService;

import java.util.List;

@Controller
public class IndexController {

    final TestService testService;

    public IndexController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String index(Model model) {
        List<Test> tests = testService.loadTests();
        model.addAttribute("tests", tests);
        model.addAttribute("create", 0);
        return "index";
    }
}
