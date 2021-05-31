package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "students_questions_answers", schema = "public", catalog = "omqkpgej")
public class StudentsQuestionsAnswers {
    @Id
    @Column(name = "id", nullable = false)
    Long id;
    @Column(name="student_id")
    Long studentId;
    @Column(name="question_id")
    Long questionId;
    @Column(name="answer_id")
    Long answerId;
}
