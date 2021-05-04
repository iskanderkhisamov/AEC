package ru.kstu.aec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
    String[] questions = new String[] { "Кто такой Сократ?", "Как умер Сократ?"};
    String[] answers1 = new String[] { "Солдат", "Философ", "Сатирик" };
    String[] answers2 = new String[] { "Отравлен", "Растрелян", "Утонул" };

    @GetMapping("/test/{id}")
    public String Test(Model model, @PathVariable String id) {
        model.addAttribute("questions", questions);
        model.addAttribute("answers1", answers1);
        model.addAttribute("answers2", answers2);
        model.addAttribute("question", questions[Integer.parseInt(id)]);
        model.addAttribute("question_id", Integer.parseInt(id));

        int last = questions.length;
        model.addAttribute("last", last);

        return "test";
    }

    @GetMapping("/test")
    public String Test(Model model) {
        model.addAttribute("questions", questions);
        model.addAttribute("answers1", answers1);
        model.addAttribute("answers2", answers2);
        return "test";
    }
}
