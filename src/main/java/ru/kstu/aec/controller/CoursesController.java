package ru.kstu.aec.controller;

import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.Paragraph;
import ru.kstu.aec.models.Theme;
import ru.kstu.aec.services.CourseService;
import ru.kstu.aec.services.ParagraphService;
import ru.kstu.aec.services.SpecializationService;
import ru.kstu.aec.services.ThemeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class CoursesController {

    final CourseService courseService;
    final SpecializationService specializationService;
    final ThemeService themeService;
    final ParagraphService paragraphService;

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.loadCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String getCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourse(id);
        Set<Theme> themes = course.getThemes();
        System.out.println(themes.size());
        for (Theme theme : themes) {
            System.out.println(theme.getName());
        }
        model.addAttribute("course", course);
        model.addAttribute("themes", themes);
        return "course";
    }

    @GetMapping("/chapter/{id}")
    public String getChapters(@PathVariable Long id, Model model) {
        Theme theme = themeService.getTheme(id);
        model.addAttribute("theme", theme);
        return "chapter";
    }


    @GetMapping("/tests")
    public String getTests(Model model) {
        return "tests";
    }
}