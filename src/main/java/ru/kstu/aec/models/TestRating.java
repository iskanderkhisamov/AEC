package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "test_rating", schema = "public", catalog = "omqkpgej")
public class TestRating {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Double polScore;
    private Double chlScore;
    private Double globalScore;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User user;

}
