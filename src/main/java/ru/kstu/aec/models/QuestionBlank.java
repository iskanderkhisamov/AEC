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

    private Long category;

    @SneakyThrows
    public Question toQuestion(CategoryService categoryService) {
        Question question = new Question();
        question.setCategory(categoryService.getCategory(category));
        question.setText(text);
        return question;
    }
}
