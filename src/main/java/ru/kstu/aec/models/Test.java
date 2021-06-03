package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(exclude={"author","statistics","questions"})
@Getter
@Setter
@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "test")
    private List<Statistic> statistics = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "test")
    private List<Question> questions = new ArrayList<>();
}
