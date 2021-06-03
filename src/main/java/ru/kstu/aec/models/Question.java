package ru.kstu.aec.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"category","tests","rightAnswer","answers"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "right_answer_id")
    private Answer rightAnswer;

    @ManyToMany(mappedBy = "questions")
    private List<Test> tests = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "questions_answers", joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answers = new ArrayList<>(4);

}
