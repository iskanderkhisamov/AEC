package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kstu.aec.models.*;
import ru.kstu.aec.models.DTO.QuestionDTO;
import ru.kstu.aec.services.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Controller
public class TestController {

    private final UserService userService;
    private final TestService testService;
    private final QuestionService questionService;
    private final StatisticService statisticService;
    private final CourseService courseService;
    private final ChapterService chapterService;
    private int qid;
    private int last;
    private int first;

    List<Question> questions = new ArrayList<>();
    Set<QuestionDTO> questionDtos = new HashSet<>();
    Course course;
    Chapter chapter;
    Test test;
    Question question;

    public TestController(QuestionService questionService, UserService userService, TestService testService, StatisticService statisticService, CourseService courseService, ChapterService chapterService) {
        this.questionService = questionService;
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
    public String getCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapters", chapterService.getChapters());
        course = courseService.getCourse(id);
        return "course";
    }

    @GetMapping("/course/{id}/chapter/{cid}")
    public String getChapter(@PathVariable Long id, @PathVariable Long cid, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("tests", chapterService.getChapter(cid).getTests());
        chapter = chapterService.getChapter(cid);
        return "chapter";
    }

    @GetMapping("/course/{id}/chapter/{cid}/test/{tid}")
    public String getTest(@PathVariable Long id, @PathVariable Long cid, @PathVariable Long tid, Model model, QuestionDTO questionDTO) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("chapter", chapterService.getChapter(cid));
        model.addAttribute("test", testService.getTest(tid));
        model.addAttribute("questionDTO", questionDTO);
        questions = testService.getTest(tid).getQuestions();
        last = questions.size() - 1;
        first = 0;
        qid = 0;
        model.addAttribute("count", qid);
        test = testService.getTest(tid);
        return "redirect:/course/"+course.getId()+"/chapter/"+chapter.getId()+"/test/"+test.getId()+"/question/0";
    }

    @GetMapping("/course/{id}/chapter/{cid}/test/{tid}/question/{qid}")
    public String getQuestion(@PathVariable int qid, Model model, QuestionDTO questionDTO) {
        question = questionService.getQuestion(questions.get(qid).getId());
        model.addAttribute("questionDTO", questionDTO);
        model.addAttribute("count", qid);
        model.addAttribute("first", first);
        model.addAttribute("last", last);
        model.addAttribute("course", course);
        model.addAttribute("chapter", chapter);
        model.addAttribute("test", test);
        model.addAttribute("question", question);
        model.addAttribute("answers", question.getAnswers());
        model.addAttribute("count", qid);
        return "test";
    }

    @PostMapping("/test/previous")
    public String postTestPrevious(@ModelAttribute QuestionDTO questionDTO, BindingResult result) throws Exception {
        questionDTO.setId(questions.get(qid).getId());
        if(questionDTO.getAnswer() == null) {
            Long id = null;
            for(int i = 0; question.getRightAnswer() != question.getAnswers().get(i) && i < question.getAnswers().size(); i++) {
                questionDTO.setAnswer(question.getAnswers().get(i).getId());
            }
        }
        questionDtos.add(questionDTO);
        qid -= 1;
        return "redirect:/course/"+course.getId()+"/chapter/"+chapter.getId()+"/test/"+test.getId()+"/question/"+qid;
    }

    @PostMapping("/test/next")
    public String postTestNext(@ModelAttribute QuestionDTO questionDTO, BindingResult result) throws Exception {
        questionDTO.setId(questions.get(qid).getId());
        questionDtos.add(questionDTO);
        qid += 1;
        return "redirect:/course/"+course.getId()+"/chapter/"+chapter.getId()+"/test/"+test.getId()+"/question/"+qid;
    }

    @PostMapping("/test/end")
    public String postTest(@ModelAttribute QuestionDTO questionDTO, BindingResult result) throws Exception {
        User user = userService.loadUserByUsername(((User) getAuthentication().getPrincipal()).getEmail());
        questionDTO.setId(questions.get(qid).getId());
        questionDtos.add(questionDTO);
        Statistic statistic = new Statistic();
        statistic.setTest(testService.getTest(test.getId()));
        for(int i = 0; i < user.getStatistics().size(); i++) {
            if(user.getStatistics().get(i).getTest().getId().equals(statistic.getTest().getId())) {
                statisticService.deleteStatistic(user.getStatistics().get(i));
            }
        }
        statistic.setUser(user);
        for (QuestionDTO q : questionDtos) {
            Question question = questionService.getQuestion(q.getId());
            if (question.getCategory().getName().equals("POL")) {
                System.out.println("pol чекаем");
                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
                    System.out.println("pol правильный");
                    statistic.setPol(statistic.getPol() + question.getCategory().getRating());
                }
            }
            else if (question.getCategory().getName().equals("UPR")) {
                System.out.println("upr чекаем");
                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
                    System.out.println("upr правильный");
                    statistic.setUpr(statistic.getUpr() + question.getCategory().getRating());
                }
            }
            else if (question.getCategory().getName().equals("CHL")) {
                System.out.println("chl чекаем");
                if (q.getAnswer().equals(question.getRightAnswer().getId())) {
                    System.out.println("chl правильный");
                    statistic.setChl(statistic.getChl() + question.getCategory().getRating());
                }
            }
        }
        System.out.println("pol=" + statistic.getPol());
        System.out.println("chl=" + statistic.getChl());
        System.out.println("upr=" + statistic.getUpr());
        statisticService.saveStatistic(statistic);

        int plus = statistic.getPol()/5 + statistic.getChl()/15 + statistic.getUpr()/30;
        int all = questionDtos.size();
        int pol = statistic.getPol()/5;
        int chl = statistic.getChl()/15;
        int upr = statistic.getUpr()/30;
        return "redirect:/test_result/" + plus + "/" + all + "/" + pol + "/" + chl + "/" + upr + "/" + user.getId();
    }

    @GetMapping("/test_result/{plus}/{all}/{pol}/{chl}/{upr}/{id}")
    public String getTestResult(Model model, @PathVariable int plus, @PathVariable int all, @PathVariable int pol, @PathVariable int chl, @PathVariable int upr, @PathVariable Long id) {
        model.addAttribute("plus", plus);
        model.addAttribute("all", all);
        model.addAttribute("pol", pol);
        model.addAttribute("chl", chl);
        model.addAttribute("upr", upr);
        model.addAttribute("id", id);
        return "test_result";
    }
}
