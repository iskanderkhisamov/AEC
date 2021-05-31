package ru.kstu.aec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class ProfileController {

    final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "profile";
    }
    // делаем тож самое шо в индексе и добавляем инфу о пользователе
    // надо будет вам сделать так шобы ещё инфа о курсах и резах тестов отображалась

    @GetMapping("/profile/changeinfo")
    public String getChangeInfo(Model model) {
        model.addAttribute("user", ((User) getAuthentication().getPrincipal()));
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "changeinfo";
    }

    @PostMapping("/profile/changeinfo")
    public String postChangeInfo(@ModelAttribute("user") User user, BindingResult result) {
        final User olduser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserFirstName(olduser, user.getFirstname());
        userService.changeUserSecondName(olduser, user.getSurname());
        userService.changeUserEmail(olduser, user.getEmail());
        userService.changeUserPassword(olduser, user.getPassword());
        return "redirect:/profile";
    }

    @GetMapping("/profile/tocreator")
    public String getToCreator(Model model) {
        return "tocreator";
    }

    @GetMapping("/profile/admin")
    public String getProfileAdmin(Model model) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = userService.loadUsers();
        List<Integer> ids = new ArrayList<>();

        for(int i = 0; i < users.size(); i++) {
            //ids.add(users.get(i).getId());
            //if (user.getId() == users.get(i).getId())
                //users.remove(user.getId());
        }
        model.addAttribute("users", userService.loadUsers());
        return "admin";
    }
}
