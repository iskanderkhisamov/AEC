package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "students_questions_answers")
public class StudentsQuestionsAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Column(name="student_id")
    Long studentId;
    @Column(name="question_id")
    Long questionId;
    @Column(name="answer_id")
    Long answerId;
}
