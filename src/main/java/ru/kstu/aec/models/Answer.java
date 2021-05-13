package ru.kstu.aec.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="answer_to_question")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int question_id;

    private String answer_text;

    private String docs_filenames;

    private boolean is_correct;

}
