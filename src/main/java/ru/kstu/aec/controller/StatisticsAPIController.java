package ru.kstu.aec.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ru.kstu.aec.models.*;
import ru.kstu.aec.models.DTO.ChapterAPI;
import ru.kstu.aec.models.DTO.CourseAPI;
import ru.kstu.aec.models.DTO.TestAPI;
import ru.kstu.aec.models.DTO.UserAPI;
import ru.kstu.aec.services.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class StatisticsAPIController {

    private final UserService userService;
    private final TestService testService;
    private final ChapterService chapterService;
    private final CourseService courseService;
    private final QuestionService questionService;
    private final ContextComponent contextComponent;

    User user;

    @Autowired
    public StatisticsAPIController(UserService userService, TestService testService,
                                   ChapterService chapterService, CourseService courseService,
                                   QuestionService questionService, ContextComponent contextComponent) {
        this.userService = userService;
        this.testService = testService;
        this.chapterService = chapterService;
        this.courseService = courseService;
        this.questionService = questionService;
        this.contextComponent = contextComponent;
    }

    private List<Question> helper(Statistic statistic) throws Exception {
        Test test = testService.getTest(statistic.getTest().getId());
        List<Question> questionList = test.getQuestions();
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            questions.add(questionService.getQuestion(questionList.get(i).getId()));
        }
        return questions;
    }

    @SneakyThrows
    public int testUpr(Statistic statistic) {
        int poly;
        int max = 0;
        poly = statistic.getUpr();
        List<Question> questions = helper(statistic);
        for (Question q : questions) {
            if (q.getCategory().getName().equals("UPR")) {
                max = max + q.getCategory().getRating();
            }
        }
        double upr;
        upr = (double) poly / max;
        System.out.println("upr%=" + (int) Math.floor(upr * 100));
        System.out.println("upr=" + upr);
        System.out.println("max=" + max);
        System.out.println("poly=" + poly);
        return (int) Math.floor(upr * 100);
    }

    @SneakyThrows
    public int testChl(Statistic statistic) {
        int poly;
        int max = 0;
        poly = statistic.getChl();
        List<Question> questions = helper(statistic);
        for (Question q : questions) {
            if (q.getCategory().getName().equals("CHL")) {
                max = max + q.getCategory().getRating();
            }
        }
        double chl;
        chl = (double) poly / max;
        System.out.println("chl%=" + (int) Math.floor(chl * 100));
        System.out.println("chl=" + chl);
        System.out.println("max=" + max);
        System.out.println("poly=" + poly);
        return (int) Math.floor(chl * 100);
    }

    @SneakyThrows
    public int testPol(Statistic statistic) {
        int poly;
        int max = 0;
        poly = statistic.getPol();
        List<Question> questions = helper(statistic);
        for (Question q : questions) {
            if (q.getCategory().getName().equals("POL")) {
                max = max + q.getCategory().getRating();
            }
        }
        double pol;
        pol = (double) poly / max;
        System.out.println("pol%=" + (int) Math.floor(pol * 100));
        System.out.println("pol=" + pol);
        System.out.println("max=" + max);
        System.out.println("poly=" + poly);
        return (int) Math.floor(pol * 100);
    }

    public TestAPI test(int id) {
        TestAPI testAPI = new TestAPI();
        testAPI.setId(user.getStatistics().get(id).getTest().getId());
        testAPI.setName(user.getStatistics().get(id).getTest().getName());
        testAPI.setChapter(user.getStatistics().get(id).getTest().getChapter().getId());
        testAPI.setUpr(testUpr(user.getStatistics().get(id)));
        testAPI.setPol(testPol(user.getStatistics().get(id)));
        testAPI.setChl(testChl(user.getStatistics().get(id)));
        return testAPI;
    }

    public List<ChapterAPI> chapter(List<TestAPI> testApis) {
        List<ChapterAPI> chapterApis = new ArrayList<>();
        for(TestAPI testAPI : testApis) {
            Chapter chapter = chapterService.getChapter(testAPI.getChapter());
            ChapterAPI chapterAPI = new ChapterAPI();
            chapterAPI.setId(chapter.getId());
            int k = -1;
            for(int i = 0; i < chapterApis.size(); i++) {
                if(chapterAPI.getId().equals(chapterApis.get(i).getId())) {
                    k = i;
                    break;
                }
            }
            if(k > -1) {
                chapterApis.get(k).getTests().add(testAPI);
            }
            else {
                chapterAPI.setCourse(chapter.getCourse().getId());
                chapterAPI.setName(chapter.getName());
                chapterAPI.getTests().add(testAPI);
                chapterApis.add(chapterAPI);
            }
        }
        for(ChapterAPI chapterAPI : chapterApis) {
            int chl = 0;
            int upr = 0;
            int pol = 0;
            for(TestAPI testAPI : chapterAPI.getTests()) {
                chl += testAPI.getChl();
                upr += testAPI.getUpr();
                pol += testAPI.getPol();
            }
            chapterAPI.setChl(chl / chapterAPI.getTests().size());
            chapterAPI.setUpr(upr / chapterAPI.getTests().size());
            chapterAPI.setPol(pol / chapterAPI.getTests().size());
        }
        return chapterApis;
    }

    public List<CourseAPI> course(List<ChapterAPI> chapterApis) {
        List<CourseAPI> courseApis = new ArrayList<>();
        for(ChapterAPI chapterAPI : chapterApis) {
            Course course = courseService.getCourse(chapterAPI.getCourse());
            CourseAPI courseAPI = new CourseAPI();
            courseAPI.setId(course.getId());
            int k = -1;
            for(int i = 0; i < courseApis.size(); i++) {
                if(courseAPI.getId().equals(courseApis.get(i).getId())) {
                    k = i;
                    break;
                }
            }
            if(k > -1) {
                courseApis.get(k).getChapters().add(chapterAPI);
            }
            else {
                courseAPI.setUser(course.getUser().getId());
                courseAPI.setName(course.getName());
                courseAPI.getChapters().add(chapterAPI);
                courseApis.add(courseAPI);
            }
        }
        for(CourseAPI courseAPI : courseApis) {
            int chl = 0;
            int upr = 0;
            int pol = 0;
            for(ChapterAPI chapterAPI : courseAPI.getChapters()) {
                chl += chapterAPI.getChl();
                upr += chapterAPI.getUpr();
                pol += chapterAPI.getPol();
            }
            courseAPI.setChl(chl / courseAPI.getChapters().size());
            courseAPI.setUpr(upr / courseAPI.getChapters().size());
            courseAPI.setPol(pol / courseAPI.getChapters().size());
        }
        return courseApis;
    }

    @SneakyThrows
    @GetMapping("/statistics/help")
    public int help() {
        Thread.sleep(1000);
        return 1;
    }

    @GetMapping("/statistics/user/{id}")
    public UserAPI statisticsUser(@PathVariable Long id) {
        try {
            user = userService.getUser(id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        UserAPI userAPI = new UserAPI();
        try {
            userAPI.setId(user.getId());
            userAPI.setFullname(user.getFullName());
            List<TestAPI> testApis = new ArrayList<>();
            for (int i = 0; i < user.getStatistics().size(); i++) {
                testApis.add(test(i));
            }
            List<ChapterAPI> chapterApis = chapter(testApis);
            List<CourseAPI> courseApis = course(chapterApis);
            userAPI.setCourses(courseApis);
            int chl = 0;
            int upr = 0;
            int pol = 0;
            for (CourseAPI courseAPI : userAPI.getCourses()) {
                chl += courseAPI.getChl();
                upr += courseAPI.getUpr();
                pol += courseAPI.getPol();
            }
            userAPI.setChl(chl / userAPI.getCourses().size());
            userAPI.setUpr(upr / userAPI.getCourses().size());
            userAPI.setPol(pol / userAPI.getCourses().size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return userAPI;
    }
}
