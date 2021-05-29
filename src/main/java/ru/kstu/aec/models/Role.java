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
@Table(name = "roles", schema = "public", catalog = "omqkpgej")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

}
