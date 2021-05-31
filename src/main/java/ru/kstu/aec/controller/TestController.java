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
import ru.kstu.aec.services.AnswerService;
import ru.kstu.aec.services.QuestionService;

import java.util.List;

@Controller
public class TestController {

    final AnswerService answerService;
    final QuestionService questionService;
    final StudentsQuestionsAnswersService studentsQuestionsAnswersService;
    List<Question> questions;
    List<Answer> answers;

    public TestController(QuestionService questionService, AnswerService answerService, StudentsQuestionsAnswersService studentsQuestionsAnswersService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.studentsQuestionsAnswersService = studentsQuestionsAnswersService;
    }

    @GetMapping("/test/{id}")
    public String Test(Model model, @PathVariable String id, StudentsQuestionsAnswers studentsQuestionsAnswers) {
        questions = questionService.loadQuestions();
        answers = answerService.loadAnswers();

        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(Integer.parseInt(id)));
        model.addAttribute("id", Integer.parseInt(id));
        model.addAttribute("answers", answers);

        return "test";
    }

    @GetMapping("/test")
    public String getTest(Model model, StudentsQuestionsAnswers studentsQuestionsAnswers) {
        String id = "4";

        questions = questionService.loadQuestions();
        answers = answerService.loadAnswers();
        for(int i = 0; i < answers.size(); i++) {
            System.out.println(answers.get(i).getAnswer_text());
        }

        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(Integer.parseInt(id)));
        model.addAttribute("id", Integer.parseInt(id));
        model.addAttribute("answers", answers);

        return "test";
    }

    @PostMapping("/test")
    public String postTest(@ModelAttribute("studentsQuestionsAnswers") StudentsQuestionsAnswers studentsQuestionsAnswers, BindingResult result) {
        System.out.println("ТУТТТТТТТТТТТТТТТТТТТТТТТТТТТТТ ПОССССССССССССССССССССССССССССССССТ!!!");
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        studentsQuestionsAnswers.setStudentId(user.getId());
        Long id = studentsQuestionsAnswers.getAnswerId();
        studentsQuestionsAnswers.setQuestionId(answerService.getAnswer(id).getQuestion_id());
        System.out.println(studentsQuestionsAnswers.getStudentId());
        System.out.println(studentsQuestionsAnswers.getQuestionId());
        System.out.println(studentsQuestionsAnswers.getAnswerId());
        studentsQuestionsAnswersService.save(studentsQuestionsAnswers);
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
