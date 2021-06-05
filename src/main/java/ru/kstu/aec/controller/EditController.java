package ru.kstu.aec.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kstu.aec.models.*;
import ru.kstu.aec.services.*;

@Controller
@RequestMapping("/edit")
public class EditController {

    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final CourseService courseService;
    private final ChapterService chapterService;

    Course course;
    Chapter chapter;
    Test test;
    Question question;
    Answer answer;

    public EditController(UserService userService, TestService testService, QuestionService questionService, AnswerService answerService, CategoryService categoryService, CourseService courseService, ChapterService chapterService) {
        this.userService = userService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable Long id, Model model) {
        course = courseService.getCourse(id);
        model.addAttribute("course", course);
        return "edit_course_params";
    }

    @GetMapping("/chapter/{id}")
    public String getChapter(@PathVariable Long id, Model model) {
        chapter = chapterService.getChapter(id);
        model.addAttribute("chapter", chapter);
        return "edit_chapter_params";
    }

    @GetMapping("/test/{id}")
    public String getTest(@PathVariable Long id, Model model) {
        test = testService.getTest(id);
        model.addAttribute("test", test);
        return "edit_test_params";
    }

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable Long id, Model model) {
        question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("categories", categoryService.loadCategories());
        model.addAttribute("answers", questionService.getQuestion(id).getAnswers());
        return "edit_question_params";
    }

    @GetMapping("/answer/{id}")
    public String getAnswer(@PathVariable Long id, Model model) {
        answer = answerService.getAnswer(id);
        model.addAttribute("answer", answer);
        return "edit_answer_params";
    }

    @SneakyThrows
    @PostMapping("/course")
    public String postCourse(@ModelAttribute Course course, BindingResult bindingResult) {
        course.setId(this.course.getId());
        course.setChapters(this.course.getChapters());
        course.setUser(this.course.getUser());
        if(course.getName() == null) {
            course.setName(this.course.getName());
        }
        if(course.getDescription() == null) {
            course.setDescription(this.course.getDescription());
        }
        courseService.saveCourse(course);
        return "redirect:/profile/courses";
    }

    @SneakyThrows
    @PostMapping("/chapter")
    public String postChapter(@ModelAttribute("chapter") Chapter chapter, BindingResult bindingResult) {
        chapter.setCourse(this.chapter.getCourse());
        chapter.setTests(this.chapter.getTests());
        chapter.setId(this.chapter.getId());
        if(chapter.getName() == null) {
            chapter.setName(this.chapter.getName());
        }
        chapterService.saveChapter(chapter);
        return "redirect:/profile/courses";
    }

    @SneakyThrows
    @PostMapping("/test")
    public String postTest(@ModelAttribute Test test, BindingResult bindingResult) {
        test.setId(this.test.getId());
        test.setChapter(this.test.getChapter());
        test.setStatistics(this.test.getStatistics());
        test.setQuestions(this.test.getQuestions());
        if(test.getName() == null) {
            test.setName(this.test.getName());
        }
        testService.saveTest(test);
        return "redirect:/profile/courses";
    }

    @SneakyThrows
    @PostMapping("/question")
    public String postQuestion(@ModelAttribute("question") QuestionEdit question, BindingResult bindingResult) {
        Question q = question.toQuestion(answerService, categoryService);
        q.setId(this.question.getId());
        q.setAnswers(this.question.getAnswers());
        q.setTest(this.question.getTest());
        if(q.getText() == null) {
            q.setText(this.question.getText());
        }
        if(q.getCategory() == null) {
            q.setCategory(this.question.getCategory());
        }
        if(q.getRightAnswer() == null) {
            q.setRightAnswer(this.question.getRightAnswer());
        }
        questionService.saveQuestion(q);
        return "redirect:/profile/courses";
    }

    @SneakyThrows
    @PostMapping("/answer")
    public String postAnswer(@ModelAttribute("answer") Answer answer, BindingResult bindingResult) {
        answer.setQuestion(this.answer.getQuestion());
        answer.setId(this.answer.getId());
        if(answer.getText() == null) {
            answer.setText(this.answer.getText());
        }
        answerService.saveAnswer(answer);
        return "redirect:/profile/courses";
    }
}
