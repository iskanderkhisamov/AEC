package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "tests")
public class Test {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tests")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "test")
    private Set<Statistic> statistics = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tests_questions", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions = new HashSet<>();
}
