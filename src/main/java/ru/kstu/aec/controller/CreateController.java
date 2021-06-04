package ru.kstu.aec.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.*;

import java.util.ArrayList;
import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
@RequestMapping("/profile/create")
public class CreateController {

    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final CourseService courseService;
    private final ChapterService chapterService;

    Test current = null;
    List<Answer> answers = new ArrayList<>();
    List<Question> questions =  new ArrayList<>();

    public CreateController(UserService userService, TestService testService, QuestionService questionService, AnswerService answerService, CategoryService categoryService, CourseService courseService, ChapterService chapterService) {
        this.userService = userService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("/answer")
    public String getUserCreateTest(Model model, AnswerBlank answers) {
        model.addAttribute("answers", answers);
        return "create_answer";
    }

    @GetMapping("/question")
    public String getUserCreateTest(Model model, QuestionBlank question) {
        model.addAttribute("question", question);
        for (Answer a : answers) {
            System.out.println(a.getText());
        }
        model.addAttribute("answers", answers);
        model.addAttribute("categories", categoryService.loadCategories());
        return "create_question";
    }

    @GetMapping("/test")
    public String getUserCreateTest(Model model, Test test) {
        model.addAttribute("test", test);
        return "create_test";
    }

    @GetMapping("/course")
    public String getUserCreateTest(Model model, Course course) {
        model.addAttribute("course", course);
        return "create_course";
    }

    @SneakyThrows
    @PostMapping("/answer")
    public String postAnswer(@ModelAttribute AnswerBlank answer, BindingResult bindingResult) {
        for (Answer ans : answer.toAnswerList()) {
            answerService.createAnswer(ans);
            System.out.println(ans.getText());
            answers.add(answerService.getAnswer());
        }
        return "redirect:/profile/create/question";
    }

    @SneakyThrows
    @PostMapping("/question")
    public String postQuestion(@ModelAttribute("question") QuestionBlank question, BindingResult bindingResult) {
        Question question1 = question.toQuestion(answerService, categoryService);
        question1.setAnswers(answers);
        questions.add(question1);
        question1.setTest(current);
        questionService.createQuestion(question1);
        return "redirect:/profile/create/answer";
    }

    @SneakyThrows
    @PostMapping("/test")
    public String postTest(@ModelAttribute Test test, BindingResult bindingResult) {
        test.setQuestions(questions);
        testService.saveTest(test);
        return "redirect:/profile";
    }

    @SneakyThrows
    @PostMapping("/course")
    public String postCourse(@ModelAttribute Course course, BindingResult bindingResult) {
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        course.setUser(user);
        courseService.saveCourse(course);
        return "redirect:/profile";
    }

}
