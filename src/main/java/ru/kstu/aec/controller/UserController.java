package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.User;
import ru.kstu.aec.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(User user) {
        return "registration";
    }
    // тут тож самое шо в CreateController

    @PostMapping("/registration")
    String signUp(@ModelAttribute("user") User user,
                  BindingResult result) {
        userService.signUpUser(user);
        return "redirect:/login";
    }
    // ну а тут мы регаем пользователя и перенапрявляем на login
    // но вы можете подзапариться и сделать так шобы сразу после регистрации мы были авторизованы

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
