package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;
import static ru.kstu.aec.configs.SecurityConfig.isTeacher;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        isTeacher(model);
        System.out.println(getAuthentication());
        return "index";
    }
    // юзаем метод, который у нас в SecurityConfig описан, шобы страничка норм реагировала на разный тип юзеров
    // и выводим аутентификационный токен

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
