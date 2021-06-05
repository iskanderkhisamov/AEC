package ru.kstu.aec.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.*;

import java.util.ArrayList;
import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final CourseService courseService;
    private final ChapterService chapterService;

    public ProfileController(UserService userService, TestService testService, QuestionService questionService, CourseService courseService, ChapterService chapterService) {
        this.userService = userService;
        this.testService = testService;
        this.questionService = questionService;
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("")
    public String getProfile(Model model) {
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        model.addAttribute("id", ((User) getAuthentication().getPrincipal()).getId());
        return "profile";
    }

    @GetMapping("/edit")
    public String getChangeInfo(Model model) {
        model.addAttribute("user", getAuthentication().getPrincipal());
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "edit";
    }

    @PostMapping("/edit")
    public String postChangeInfo(@ModelAttribute("user") User user, BindingResult result) {
        final User oldUser = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        userService.changeUserFirstName(oldUser, user.getFirstname());
        userService.changeUserSecondName(oldUser, user.getSurname());
        userService.changeUserEmail(oldUser, user.getEmail());
        userService.changeUserPassword(oldUser, user.getPassword());
        return "redirect:/profile";
    }

    @SneakyThrows
    @GetMapping("/courses")
    public String getCourses(Model model) {
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        List<Course> courses = courseService.getCoursesByUser(user);
        model.addAttribute("courses", courses);
        return "edit_courses";
    }

    @SneakyThrows
    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        return "edit_course";
    }

    @SneakyThrows
    @GetMapping("/course/{id}/chapter/{cid}")
    public String getChapter(@PathVariable Long id,@PathVariable Long cid, Model model) {
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("tests", chapterService.getChapter(cid).getTests());
        return "edit_chapter";
    }

    @SneakyThrows
    @GetMapping("/course/{id}/chapter/{cid}/test/{tid}")
    public String getChapter(@PathVariable Long id,@PathVariable Long cid, @PathVariable Long tid, Model model) {
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("test", testService.getTest(tid));
        return "edit_test";
    }

    @SneakyThrows
    @GetMapping("/course/{id}/chapter/{cid}/test/{tid}/question/{qid}")
    public String getChapter(@PathVariable Long id, @PathVariable Long cid,
                             @PathVariable Long tid, @PathVariable Long qid,
                             Model model) {
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("test", testService.getTest(tid));
        model.addAttribute("question", questionService.getQuestion(qid));
        return "edit_question";
    }

    @GetMapping("/admin")
    public String getProfileAdmin(Model model) {
        final User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        List<User> users = userService.loadUsers();
        List<Integer> ids = new ArrayList<>();
        model.addAttribute("users", userService.loadUsers());
        return "admin";
    }

    @SneakyThrows
    @GetMapping("/delete/test/{id}")
    public String getUserTests(@PathVariable Long id, Model model) {
        testService.deleteTest(testService.getTest(id));
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        return "redirect:/profile/tests";
    }

}
