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
import java.util.Collections;
import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class ProfileController {

    final UserService userService;
    final TestService testService;
    final QuestionService questionService;
    final AnswerService answerService;
    final CategoryService categoryService;

    Test current = null;
    List<Answer> answers = new ArrayList<>();
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
    public String getUserTests(Model model, Test test) {
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        List<Test> tests = testService.findAllbyAuthor(user);
        model.addAttribute("tests", tests);
        model.addAttribute("test", test);
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
        for (Answer a : answers) {
            System.out.println(a.getText());
        }
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
    public String editTest(@PathVariable Long id, Model model) {
        model.addAttribute("test", new TestEdit(testService.getTest(id)));
        model.addAttribute("oldTest", testService.getTest(id));
        model.addAttribute("categories", categoryService.loadCategories());
        List<Answer> right = new ArrayList<>();
        for(Question q : testService.getTest(id).getQuestions()) {
            right.add(q.getRightAnswer());
        }
        model.addAttribute("right", right);
        current = testService.getTest(id);
        return "edit_test";
    }

    @SneakyThrows
    @GetMapping("/profile/delete/test/{id}")
    public String getUserTests(@PathVariable Long id, Model model) {
        System.out.println(id);
        testService.deleteTest(testService.getTest(id));
        System.out.println(id);
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        System.out.println(id);
        List<Test> tests = testService.findAllbyAuthor(user);
        System.out.println(id);
        model.addAttribute("tests", tests);
        System.out.println(id);
        return "redirect:/profile/tests";
    }

    @SneakyThrows
    @PostMapping("/profile/create/answer")
    public String postAnswer(@ModelAttribute AnswerBlank answer, BindingResult bindingResult) {
        for (Answer ans : answer.toAnswerList()) {
            answerService.createAnswer(ans);
            System.out.println(ans.getText());
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
        question1.setTest(current);
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
    public String editAnswer(@ModelAttribute("test") TestEdit test, BindingResult bindingResult) {
        Test t = new Test();
        t.setId(current.getId());
        t.setAuthor(current.getAuthor());
        t.setStatistics(current.getStatistics());
        t.setName(test.getName());
        List<Question> questions = new ArrayList<>();
        for(int i = 0; i < test.getQuestions().size(); i++) {
            QuestionEdit q = test.getQuestions().get(i);
            Question question = current.getQuestions().get(i);
            for(int j = 0; j < q.getAnswers().size(); j++) {
                Answer answer = current.getQuestions().get(i).getAnswers().get(j);
                answer.setText(test.getQuestions().get(i).getAnswers().get(j).getText());
                question.getAnswers().add(answer);
            }
            question.setCategory(categoryService.getCategory(q.getCategory()));
            question.setText(q.getText());
            question.setRightAnswer(answerService.getAnswer(q.getRightAnswer()));
            questions.add(question);
        }
        t.setQuestions(questions);
        System.out.println(t.getId());
        System.out.println(t.getName());
        System.out.println(t.getAuthor().getUsername());
        System.out.println(t.getQuestions().get(0).getId());
        System.out.println(t.getQuestions().get(0).getRightAnswer().getId());
        System.out.println(t.getQuestions().get(0).getCategory().getId());
        testService.saveTest(t);
        return "redirect:/profile";
    }

}
