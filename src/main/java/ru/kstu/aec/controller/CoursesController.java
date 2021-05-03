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

    @GetMapping("/course")
    public String getCourse(Model model) {
        return "course";
    }

    @GetMapping("/chapters")
    public String getChapters(Model model) {
        return "chapters";
    }

    @GetMapping("/chapter")
    public String getChapter(Model model) {
        return "chapter";
    }

    @GetMapping("/tests")
    public String getTests(Model model) {
        return "tests";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        return "test";
    }
}