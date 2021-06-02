package ru.kstu.aec.controller;

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
    int count;
    Long qid;
    int lastId;
    Long testId;

    TestDTO testDTO = new TestDTO();

    List<Question> questions;

    public TestController(QuestionService questionService, AnswerService answerService, UserService userService, TestService testService, StatisticService statisticService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
        this.testService = testService;
        this.statisticService = statisticService;
    }

    @GetMapping("/test/{id}/{qid}")
    public String Test(Model model, @PathVariable Long id, @PathVariable Long qid) throws Exception {
        System.out.println("ТЕСТ ID: " + id);
        System.out.println("ВОПРОС ID: " + qid);
        questions = testService.getTest(id).getQuestions();
        lastId = questions.size();
        System.out.println("ПОСЛЕДНИЙ ID: " + lastId);
        for (Question q : questions) {
            if (q.getId() == qid) {
                model.addAttribute("question", q);
            }
        }
        model.addAttribute("questionDTO", new QuestionDTO());
        model.addAttribute("last", lastId);
        this.qid = qid;
        testId = id;
        count++;
        model.addAttribute("count", count);
        System.out.println("Номер Массива: " + count);
        testDTO.setId(testId);
        return "test";
    }

    @PostMapping("/test/next")
    public String postTestNext(@ModelAttribute QuestionDTO questionDTO, BindingResult result) throws Exception {
        System.out.println("NEXXXXXXXXXXXXXXXXXXXXXXXXT");
        System.out.println(questionDTO.getAnswer());
        qid = questions.get(count).getId();
        questionDTO.setId(qid);
        testDTO.getQuestions().add(questionDTO);
        return "redirect:/test/" + testId + "/" + qid;
    }

    @PostMapping("/test/end")
    public String postTest(@ModelAttribute QuestionDTO questionDTO, BindingResult result) throws Exception {
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEENNNNNNNNNNNNNNNNNNNNNNNNNNNDDDDDDDDDDDD");
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        questionDTO.setId(qid);
        testDTO.getQuestions().add(questionDTO);
        Statistic statistic = new Statistic();
        statistic.setTest(testService.getTest(testId));
        statistic.setUser(user);
        for (QuestionDTO q : testDTO.getQuestions()) {
            Question question = questionService.getQuestion(q.getId());
            if (question.getCategory().getName().equals("POL")) {
                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
                    statistic.setPol(statistic.getPol() + question.getCategory().getRating());
                }
            } else if (question.getCategory().getName().equals("UPR")) {
                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
                    statistic.setUpr(statistic.getUpr() + question.getCategory().getRating());
                }
            } else if (question.getCategory().getName().equals("CHL")) {
                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
                    statistic.setChl(statistic.getChl() + question.getCategory().getRating());
                }
            }
        }
        System.out.println("pol=" + statistic.getPol());
        System.out.println("chl=" + statistic.getChl());
        System.out.println("upr=" + statistic.getUpr());
        count = 0;
        statisticService.saveStatistic(statistic);
        return "redirect:/result";
    }
}
