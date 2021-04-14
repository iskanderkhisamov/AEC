package ru.kstu.aec.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kstu.aec.services.CourseService;

@Controller
@AllArgsConstructor
public class CoursesController {

    final CourseService courseService;

    @GetMapping("/courses")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.loadCourses());
        return "courses";
    }
}
