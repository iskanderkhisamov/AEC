package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.*;

import java.util.List;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class TestController {

    private final AnswerService answerService;
    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final StatisticService statisticService;
    private final CourseService courseService;
    private final ChapterService chapterService;
    private int qid;
    private int lastId;
    private Long testId;

    List<Question> questions;

    public TestController(QuestionService questionService, AnswerService answerService, UserService userService, TestService testService, StatisticService statisticService, CourseService courseService, ChapterService chapterService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
        this.testService = testService;
        this.statisticService = statisticService;
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }

    @GetMapping("/course/{id}")
    public String getCourses(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapters", chapterService.getChapters());
        return "course";
    }

    @GetMapping("/course/{id}/chapter/{cid}")
    public String getCourses(@PathVariable Long id, @PathVariable Long cid, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("tests", chapterService.getChapter(cid).getTests());
        return "chapter";
    }

    @GetMapping("/course/{id}/chapter/{cid}/test/{tid}")
    public String getCourses(@PathVariable Long id, @PathVariable Long cid, @PathVariable Long tid, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("test", testService.getTest(tid));
        model.addAttribute("questions", testService.getTest(tid).getQuestions());
        return "test";
    }

    @GetMapping("/course/{id}/chapter/{cid}/test/{tid}/question/{qid}")
    public String getCourses(@PathVariable Long id, @PathVariable Long cid,
                             @PathVariable Long tid, @PathVariable Long qid,
                             Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("test", testService.getTest(tid));
        model.addAttribute("question", questionService.getQuestion(qid));
        model.addAttribute("answers", questionService.getQuestion(qid).getAnswers());
        return "test";
    }

    @PostMapping("/test")
    public String postTest(@ModelAttribute QuestionDTO questionDTO, BindingResult result) throws Exception {
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEENNNNNNNNNNNNNNNNNNNNNNNNNNNDDDDDDDDDDDD");
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
//        questionDTO.setId(questions.get(qid).getId());
//        testDTO.getQuestions().add(questionDTO);
        Statistic statistic = new Statistic();
        statistic.setTest(testService.getTest(testId));
        statistic.setUser(user);
//        for (QuestionDTO q : testDTO.getQuestions()) {
//            Question question = questionService.getQuestion(q.getId());
//            if (question.getCategory().getName().equals("POL")) {
//                System.out.println("pol чекаем");
//                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
//                    System.out.println("pol правильный");
//                    statistic.setPol(statistic.getPol() + question.getCategory().getRating());
//                }
//            }
//            else if (question.getCategory().getName().equals("UPR")) {
//                System.out.println("upr чекаем");
//                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
//                    System.out.println("upr правильный");
//                    statistic.setUpr(statistic.getUpr() + question.getCategory().getRating());
//                }
//            }
//            else if (question.getCategory().getName().equals("CHL")) {
//                System.out.println("chl чекаем");
//                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
//                    System.out.println("chl правильный");
//                    statistic.setChl(statistic.getChl() + question.getCategory().getRating());
//                }
//            }
//        }
        System.out.println("pol=" + statistic.getPol());
        System.out.println("chl=" + statistic.getChl());
        System.out.println("upr=" + statistic.getUpr());
        statisticService.saveStatistic(statistic);
        return "redirect:/result";
    }
}
