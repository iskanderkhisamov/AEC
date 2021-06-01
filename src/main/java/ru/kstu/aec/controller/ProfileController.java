package ru.kstu.aec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.models.User;
import ru.kstu.aec.services.TestService;
import ru.kstu.aec.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class ProfileController {

    final UserService userService;
    final TestService testService;

    public ProfileController(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String getChangeInfo(Model model) {
        model.addAttribute("user", getAuthentication().getPrincipal());
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "edit";
    }

    @PostMapping("/profile/edit")
    public String postChangeInfo(@ModelAttribute("user") User user, BindingResult result) {
        final User oldUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserFirstName(oldUser, user.getFirstname());
        userService.changeUserSecondName(oldUser, user.getSurname());
        userService.changeUserEmail(oldUser, user.getEmail());
        userService.changeUserPassword(oldUser, user.getPassword());
        return "redirect:/profile";
    }

    @GetMapping("/profile/tests")
    public String getUserTests(Model model) {
        List<Test> tests = testService.loadTests();
        model.addAttribute("tests", tests);
        return "index";
    }

    @GetMapping("/profile/admin")
    public String getProfileAdmin(Model model) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = userService.loadUsers();
        List<Integer> ids = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            //ids.add(users.get(i).getId());
            //if (user.getId() == users.get(i).getId())
            //users.remove(user.getId());
        }
        model.addAttribute("users", userService.loadUsers());
        return "admin";
    }
}
