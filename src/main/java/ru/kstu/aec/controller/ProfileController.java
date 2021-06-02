package ru.kstu.aec.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class ProfileController {

    final UserService userService;
    final TestService testService;
    final QuestionService questionService;
    final AnswerService answerService;
    final CategoryService categoryService;

    Set<Answer> answers = new HashSet<>();
    List<Question> questions =  new ArrayList<>();

    public ProfileController(UserService userService, TestService testService, QuestionService questionService, AnswerService answerService, CategoryService categoryService) {
        this.userService = userService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String getChangeInfo(Model model) {
        model.addAttribute("user", getAuthentication().getPrincipal());
        model.addAttribute("name", ((User) getAuthentication().getPrincipal()).getFirstname());
        model.addAttribute("surname", ((User) getAuthentication().getPrincipal()).getSurname());
        model.addAttribute("email", ((User) getAuthentication().getPrincipal()).getEmail());
        return "edit";
    }

    @PostMapping("/profile/edit")
    public String postChangeInfo(@ModelAttribute("user") User user, BindingResult result) {
        final User oldUser = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        userService.changeUserFirstName(oldUser, user.getFirstname());
        userService.changeUserSecondName(oldUser, user.getSurname());
        userService.changeUserEmail(oldUser, user.getEmail());
        userService.changeUserPassword(oldUser, user.getPassword());
        return "redirect:/profile";
    }

    @SneakyThrows
    @GetMapping("/profile/tests")
    public String getUserTests(Model model) {
        System.out.println("user");
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        System.out.println("set " + user.getEmail());
        model.addAttribute("user", user);
        List<Test> tests = testService.findAllbyAuthor(user);
        model.addAttribute("tests", tests);
        return "tests";
    }

    @GetMapping("/profile/create/answer")
    public String getUserCreateTest(Model model, AnswerBlank answers) {
        model.addAttribute("answers", answers);
        return "create_answer";
    }

    @GetMapping("/profile/create/question")
    public String getUserCreateTest(Model model, QuestionBlank question) {
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        model.addAttribute("categories", categoryService.loadCategories());
        return "create_question";
    }

    @GetMapping("/profile/create/test")
    public String getUserCreateTest(Model model, Test test) {
        model.addAttribute("test", test);
        return "create_test";
    }

    @GetMapping("/profile/admin")
    public String getProfileAdmin(Model model) {
        final User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        List<User> users = userService.loadUsers();
        List<Integer> ids = new ArrayList<>();
        model.addAttribute("users", userService.loadUsers());
        return "admin";
    }

    @SneakyThrows
    @GetMapping("/profile/edit/test/{id}")
    public String editTest(@PathVariable Long id, Model model, Test test) {
        model.addAttribute("oldTest", testService.getTest(id));
        return "edit_test";
    }

    @SneakyThrows
    @PostMapping("/profile/create/answer")
    public String postAnswer(@ModelAttribute AnswerBlank answer, BindingResult bindingResult) {
        for (Answer ans : answer.toAnswerList()) {
            answerService.createAnswer(ans);
            answers.add(answerService.getAnswer());
        }
        return "redirect:/profile/create/question";
    }

    @SneakyThrows
    @PostMapping("/profile/create/question")
    public String postAnswer(@ModelAttribute("question") QuestionBlank question, BindingResult bindingResult) {
        Question question1 = question.toQuestion(answerService, categoryService);
        question1.setAnswers(answers);
        questions.add(question1);
        questionService.createQuestion(question1);
        return "redirect:/profile/create/answer";
    }

    @SneakyThrows
    @PostMapping("/profile/create/test")
    public String postAnswer(@ModelAttribute Test test, BindingResult bindingResult) {
        test.setQuestions(questions);
        test.setAuthor(userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail()));
        testService.saveTest(test);
        return "redirect:/profile";
    }

    @SneakyThrows
    @PostMapping("/profile/edit/test")
    public String editAnswer(@ModelAttribute Test test, BindingResult bindingResult) {
        testService.saveTest(test);
        return "redirect:/profile";
    }

}
