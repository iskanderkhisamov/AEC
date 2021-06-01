package ru.kstu.aec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.models.User;
import ru.kstu.aec.services.TestService;
import ru.kstu.aec.services.UserService;

import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

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
        return "index";
    }
}
