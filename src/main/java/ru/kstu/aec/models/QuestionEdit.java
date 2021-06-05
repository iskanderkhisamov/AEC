package ru.kstu.aec.models;

import lombok.*;
import ru.kstu.aec.services.AnswerService;
import ru.kstu.aec.services.CategoryService;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEdit {

    private String text;

    private Long category;

    private Long rightAnswer;

    @SneakyThrows
    public Question toQuestion(AnswerService answerService, CategoryService categoryService) {
        Question question = new Question();
        if(category != null) {
            question.setCategory(categoryService.getCategory(category));
        }
        if(rightAnswer != null) {
            question.setRightAnswer(answerService.getAnswer(rightAnswer));
        }
        question.setText(text);
        return question;
    }
}