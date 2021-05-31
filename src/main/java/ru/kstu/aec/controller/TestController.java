package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.AnswerService;
import ru.kstu.aec.services.QuestionService;

import java.util.List;

@Controller
public class TestController {

    final AnswerService answerService;
    final QuestionService questionService;
    List<Question> questions;
    List<Answer> answers;

    public TestController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
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

        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(Integer.parseInt(id)));
        model.addAttribute("id", Integer.parseInt(id));
        model.addAttribute("answers", answers);

        return "test";
    }

    @PostMapping("/test")
    public String postTest(@ModelAttribute("studentsQuestionsAnswers") StudentsQuestionsAnswers studentsQuestionsAnswers, BindingResult result) {

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
