package ru.kstu.aec.models.DTO;

import lombok.*;
import ru.kstu.aec.models.Question;
import ru.kstu.aec.services.AnswerService;
import ru.kstu.aec.services.CategoryService;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBlank {

    private String text;

    private String image;

    private Long category;

    @SneakyThrows
    public Question toQuestion(CategoryService categoryService) {
        Question question = new Question();
        question.setCategory(categoryService.getCategory(category));
        question.setText(text);
        question.setImage(image);
        return question;
    }
}
