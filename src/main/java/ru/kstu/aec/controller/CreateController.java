package ru.kstu.aec.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kstu.aec.models.*;
import ru.kstu.aec.models.DTO.QuestionBlank;
import ru.kstu.aec.services.*;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
@RequestMapping("/create")
public class CreateController {

    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final CourseService courseService;
    private final ChapterService chapterService;

    Long questionId = null;
    Long testId = null;
    Long courseId = null;
    Long chapterId = null;

    public CreateController(UserService userService, TestService testService, QuestionService questionService, AnswerService answerService, CategoryService categoryService, CourseService courseService, ChapterService chapterService) {
        this.userService = userService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("/course")
    public String getCourse(Model model, Course course) {
        model.addAttribute("course", course);
        return "create_course";
    }

    @GetMapping("/course/{id}/chapter")
    public String getChapter(@PathVariable Long id, Model model, Chapter chapter) {
        model.addAttribute("chapter", chapter);
        courseId = id;
        return "create_chapter";
    }

    @GetMapping("/chapter/{id}/test")
    public String getTest(@PathVariable Long id, Model model, Test test) {
        model.addAttribute("test", test);
        chapterId = id;
        return "create_test";
    }

    @GetMapping("/test/{id}/question")
    public String getChapter(@PathVariable Long id, Model model, QuestionBlank question) {
        model.addAttribute("categories", categoryService.loadCategories());
        model.addAttribute("question", question);
        testId = id;
        return "create_question";
    }

    @GetMapping("/question/{id}/answer")
    public String getChapter(@PathVariable Long id, Model model, Answer answer) {
        model.addAttribute("answer", answer);
        questionId = id;
        return "create_answer";
    }

    @SneakyThrows
    @PostMapping("/course")
    public String postCourse(@ModelAttribute Course course, BindingResult bindingResult) {
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        course.setUser(user);
        courseService.saveCourse(course);
        return "redirect:/profile/courses";
    }

    @SneakyThrows
    @PostMapping("/chapter")
    public String postChapter(@ModelAttribute("chapter") Chapter chapter, BindingResult bindingResult) {
        chapter.setCourse(courseService.getCourse(courseId));
        chapterService.saveChapter(chapter);
        Long chid = chapter.getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        return "redirect:/profile/course/" + cid;
    }

    @SneakyThrows
    @PostMapping("/test")
    public String postTest(@ModelAttribute Test test, BindingResult bindingResult) {
        test.setChapter(chapterService.getChapter(chapterId));
        testService.saveTest(test);
        Long tid = test.getId();
        Long chid = testService.getTest(tid).getChapter().getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        return "redirect:/profile/course/" + cid + "/chapter/" + chid;
    }

    @SneakyThrows
    @PostMapping("/question")
    public String postQuestion(@ModelAttribute("question") QuestionBlank question, BindingResult bindingResult) {
        Question q = question.toQuestion(categoryService);
        q.setTest(testService.getTest(testId));
        questionService.saveQuestion(q);
        Long tid = q.getTest().getId();
        Long chid = testService.getTest(tid).getChapter().getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        return "redirect:/profile/course/" + cid + "/chapter/" + chid + "/test/" + tid;
    }

    @SneakyThrows
    @PostMapping("/answer")
    public String postAnswer(@ModelAttribute("answer") Answer answer, BindingResult bindingResult) {
        answer.setQuestion(questionService.getQuestion(questionId));
        answerService.saveAnswer(answer);
        Long qid = questionService.getQuestion(questionId).getId();
        Long tid = questionService.getQuestion(questionId).getTest().getId();
        Long chid = testService.getTest(tid).getChapter().getId();
        Long cid = chapterService.getChapter(chid).getCourse().getId();
        return "redirect:/profile/course/" + cid + "/chapter/" + chid + "/test/" + tid + "/question/" + qid;
    }

}
