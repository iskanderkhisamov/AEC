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
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getName());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("group", ((User) getAuthentication().getPrincipal()).getGruppa());
        return "profile";
    }
}
