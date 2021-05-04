package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kstu.aec.models.User;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;
import static ru.kstu.aec.configs.SecurityConfig.isTeacher;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model) {
        isTeacher(model);
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        return "profile";
    }
    // делаем тож самое шо в индексе и добавляем инфу о пользователе
    // надо будет вам сделать так шобы ещё инфа о курсах и резах тестов отображалась
}
