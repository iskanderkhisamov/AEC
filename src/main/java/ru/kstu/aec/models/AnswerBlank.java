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
public class AnswerBlank {

    private String text1;

    private String text2;

    private String text3;

    private String text4;

    public List<Answer> toAnswerList() {
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();
        Answer answer4 = new Answer();
        answer1.setText(text1);
        answer2.setText(text2);
        answer3.setText(text3);
        answer4.setText(text4);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        return answers;
    }
}
