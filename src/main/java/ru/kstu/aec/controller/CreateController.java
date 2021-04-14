package ru.kstu.aec.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.User;
import ru.kstu.aec.services.CourseService;
import ru.kstu.aec.services.UserService;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
@AllArgsConstructor
public class CreateController {

    private final CourseService courseService;
    private final UserService userService;

    @GetMapping("/create")
    public String getCreate(Course course) {
        return "create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("course") Course course,
                             BindingResult result) {
        courseService.createCourse(course);
        userService.addCourse(userService.getCourses(course, (User)getAuthentication().getPrincipal()), (User)getAuthentication().getPrincipal());
        return "redirect:/courses";
    }

}
