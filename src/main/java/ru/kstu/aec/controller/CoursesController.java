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
    // внедряем сервис для курсов

    @GetMapping("/courses")
    public String getCourses(Model model) {
        return "courses";
    }
    // кидаем в модель все курсы из бд при помощи сервиса
}
