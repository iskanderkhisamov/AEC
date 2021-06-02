package ru.kstu.aec.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBlank {

    private String text;

    private Answer answer;

    private Category category;

    private Answer[] answers = new Answer[4];

    public Question toQuestion() {
        System.out.println(answer);
        System.out.println(category);
        Question question = new Question();
        question.setAnswers(Arrays.asList(answers));
        question.setRightAnswer(answer);
        question.setCategory(category);
        question.setText(text);
        return question;
    }
}
