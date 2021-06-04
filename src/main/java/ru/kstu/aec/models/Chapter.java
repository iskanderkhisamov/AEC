package ru.kstu.aec.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"tests", "course"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "chapter")
    private List<Test> tests = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
