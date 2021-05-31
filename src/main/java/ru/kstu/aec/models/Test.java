package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private Long paragraphId;
    private Double maxPolRating;
    private Double maxChlRating;
    private Double maxGlobalRating;
}
