package ru.kstu.aec.models;

import lombok.*;
import ru.kstu.aec.services.AnswerService;
import ru.kstu.aec.services.CategoryService;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBlank {

    private String text;

    private Long answer;

    private Long category;

    @SneakyThrows
    public Question toQuestion(AnswerService answerService, CategoryService categoryService) {
        System.out.println(answer);
        System.out.println(category);
        Question question = new Question();
        question.setRightAnswer(answerService.getAnswer(answer));
        question.setCategory(categoryService.getCategory(category));
        question.setText(text);
        return question;
    }
}
