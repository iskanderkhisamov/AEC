package ru.kstu.aec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kstu.aec.models.User;
import ru.kstu.aec.models.UserRole;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("auth", true);
        System.out.println(getAuthentication());
        System.out.println(getAuthentication().getAuthorities().toArray()[0]);
        System.out.println(getAuthentication().getAuthorities().toArray()[0].equals("true"));
        return "index";
    }

    // And the getAuthentication
    private Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth;
    }


}
