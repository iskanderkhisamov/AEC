package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kstu.aec.services.*;

@Controller
@RequestMapping("/delete")
public class DeleteController {
    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CourseService courseService;
    private final ChapterService chapterService;

    public DeleteController(UserService userService, TestService testService, QuestionService questionService, AnswerService answerService, CourseService courseService, ChapterService chapterService) {
        this.userService = userService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("/course/{id}")
    public String deleteCourse(@PathVariable Long id, Model model) {
        courseService.deleteCourse(courseService.getCourse(id));
        return "redirect:/profile/courses";
    }

    @GetMapping("/chapter/{id}")
    public String deleteChapter(@PathVariable Long id, Model model) {
        Long cid = chapterService.getChapter(id).getCourse().getId();
        chapterService.deleteChapter(chapterService.getChapter(id));
        return "redirect:/profile/course/" + cid;
    }

    @GetMapping("/test/{id}")
    public String deleteTest(@PathVariable Long id, Model model) {
        Long chid = testService.getTest(id).getChapter().getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        testService.deleteTest(testService.getTest(id));
        return "redirect:/profile/course/" + cid + "/chapter/" + chid;
    }

    @GetMapping("/question/{id}")
    public String deleteQuestion(@PathVariable Long id, Model model) {
        Long tid = questionService.getQuestion(id).getTest().getId();
        Long chid = testService.getTest(tid).getChapter().getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        questionService.deleteQuestion(questionService.getQuestion(id));
        return "redirect:/profile/course/" + cid + "/chapter/" + chid + "/test/" + tid;
    }

    @GetMapping("/answer/{id}")
    public String deleteAnswer(@PathVariable Long id, Model model) {
        Long qid = answerService.getAnswer(id).getQuestion().getId();
        Long tid = questionService.getQuestion(qid).getTest().getId();
        Long chid = testService.getTest(tid).getChapter().getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        answerService.deleteAnswer(answerService.getAnswer(id));
        return "redirect:/profile/course/" + cid + "/chapter/" + chid + "/test/" + tid + "/question/" + qid;
    }

    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/";
    }

}
