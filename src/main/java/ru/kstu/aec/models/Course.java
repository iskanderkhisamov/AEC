package ru.kstu.aec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.kstu.aec.services.SpecializationService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="course", schema = "public", catalog = "omqkpgej")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacherId", nullable = false)
    private User teacher;

    private String description;

    private int level;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specializationId", nullable = false)
    private Specialization specialization;

    @OneToMany(mappedBy = "course")
    private Set<Theme> themes;

    private double max_pol_score;

    private double max_chl_score;

    private double max_upr_score;

    private double max_global_score;


}
