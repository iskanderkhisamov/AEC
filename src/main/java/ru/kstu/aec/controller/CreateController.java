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
    // внедряем сервисы

    @GetMapping("/create")
    public String getCreate(Course course) {
        return "create";
    }
    // вы спросите, а нахрена отправлять курс в гетмаппинг? а для того шобы
    // страничка могла осознавать шо в форме будем передавать

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("course") Course course,
                             BindingResult result) {
        courseService.createCourse(course);
        userService.addCourse(userService.getCourses(course, (User)getAuthentication().getPrincipal()), (User)getAuthentication().getPrincipal());
        return "redirect:/courses";
    }
    // также указываем аттрибут, но уже с аннотацией и BindingResult, который я хз зачем нужен вообще
    // создаем курс с помощью courseService и потом связываем юзера с курсом при помощи userService

}
