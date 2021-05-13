package ru.kstu.aec.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question_text;

    private int type_id;

    private int category_id;

    private String docs_filenames;

    private Double max_score;

    private int test_id;

}
