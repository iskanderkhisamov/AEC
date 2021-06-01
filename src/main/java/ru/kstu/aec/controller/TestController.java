package ru.kstu.aec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.*;

import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class TestController {

    final AnswerService answerService;
    final UserService userService;
    final TestService testService;
    final QuestionService questionService;
    final StatisticService statisticService;

    List<Question> questions;
    List<Answer> answers;

    public TestController(QuestionService questionService, AnswerService answerService, UserService userService, TestService testService, StatisticService statisticService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
        this.testService = testService;
        this.statisticService = statisticService;
    }

    @GetMapping("/test/{id}")
    public String Test(Model model, @PathVariable String id) {
        questions = questionService.loadQuestions();
        answers = answerService.loadAnswers();

        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(Integer.parseInt(id)));
        model.addAttribute("id", Integer.parseInt(id));
        model.addAttribute("answers", answers);

        return "test";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        String id = "0";

        questions = questionService.loadQuestions();
        answers = answerService.loadAnswers();
        for(int i = 0; i < answers.size(); i++) {
            System.out.println(answers.get(i).getText());
        }

        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(Integer.parseInt(id)));
        model.addAttribute("id", Integer.parseInt(id));
        model.addAttribute("answers", answers);

        return "test";
    }

    @PostMapping("/test")
    public String postTest(@ModelAttribute TestDTO test, BindingResult result) throws Exception {
        System.out.println("ТУТТТТТТТТТТТТТТТТТТТТТТТТТТТТТ ПОССССССССССССССССССССССССССССССССТ!!!");
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        Statistic statistic = new Statistic();
        statistic.setTest(testService.getTest(test.getId()));
        statistic.setUser(user);
        for(int i = 0; i < test.getQuestions().size(); i++) {
            QuestionDTO questionDTO = test.getQuestions().get(i);
            Question question = questionService.getQuestion(questionDTO.getId());
            if(question.getCategory().getName().equals("POL")) {
                if(questionDTO.getAnswer().getId() == question.getRight_answer().getId()) {
                    statistic.setPol(statistic.getPol() + question.getCategory().getRating());
                }
            }
            else if(question.getCategory().getName().equals("UPR")) {
                if(questionDTO.getAnswer().getId() == question.getRight_answer().getId()) {
                    statistic.setUpr(statistic.getUpr() + question.getCategory().getRating());
                }
            }
            else if(question.getCategory().getName().equals("CHL")) {
                if(questionDTO.getAnswer().getId() == question.getRight_answer().getId()) {
                    statistic.setChl(statistic.getChl() + question.getCategory().getRating());
                }
            }
        }

        return "redirect:/test";
    }

    @GetMapping("/crud_questions")
    public String getCrud(Question question, Answer answer) {
        return "crud_questions";
    }

    @PostMapping("/crud")
    public String postCrud(@ModelAttribute("question") Question question, BindingResult result) {
        questionService.createQuestion(question);
        return "redirect:/crud_questions";
    }

    @PostMapping("/crua")
    public String postCrua(@ModelAttribute("answer") Answer answer, BindingResult result) {
        answerService.createAnswer(answer);
        return "redirect:/crud_questions";
    }
}
