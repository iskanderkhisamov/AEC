package ru.kstu.aec.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestEdit {

    private String name;

    private List<Statistic> statistics = new ArrayList<>();

    private List<QuestionEdit> questions = new ArrayList<>();

    public TestEdit(Test test) {
        name = test.getName();
        statistics = test.getStatistics();
        for(Question q : test.getQuestions()) {
            questions.add(new QuestionEdit(q));
        }
    }

}
