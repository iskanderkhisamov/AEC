package ru.kstu.aec.controller;

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

    private final TestService testService;
    private final UserService userService;

    public IndexController(TestService testService, UserService userService) {
        this.testService = testService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        List<Test> tests = testService.loadTests();
        model.addAttribute("tests", tests);
        return "index";
    }
}
