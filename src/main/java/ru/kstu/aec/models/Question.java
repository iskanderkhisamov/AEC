package ru.kstu.aec.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"category","test","rightAnswer","answers"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "right_answer_id")
    private Answer rightAnswer;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "questions_answers", joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answers = new ArrayList<>(4);

}
