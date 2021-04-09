package ru.kstu.aec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kstu.aec.models.User;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        System.out.println(getAuthentication());
        return "index";
    }

    // And the getAuthentication
    private Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth;
    }


}
