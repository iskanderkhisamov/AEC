package ru.kstu.aec.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEdit {

    private String text;

    private Long category;

    private Long rightAnswer;

    private List<Answer> answers = new ArrayList<>(4);

    public QuestionEdit(Question question) {
        text = question.getText();
        category = question.getId();
        rightAnswer = question.getRightAnswer().getId();
        answers = question.getAnswers();
    }
}
