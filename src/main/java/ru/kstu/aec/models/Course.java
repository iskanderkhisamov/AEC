package ru.kstu.aec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int teacherId;

    private String description;

    private int level;

    private int specializationId;

    private double max_pol_score;

    private double max_chl_score;

    private double max_upr_score;

    private double max_global_score;
/*
    @JsonIgnore // не обращайте внимания
    @ManyToMany(mappedBy = "courses") // настройка ManyToMany по тому, шо у нас лежит в User
    private List<User> users;
    /*
     а тут я расскажу немного о библиотеке lombok
     она дает нам возможность не тратить время на написание геттеров и сеттеров, конструкторов и прочей херни
     ты просто пишешь нужную аннотацию сверху класса и кайфуешь
     */
    /*
     а этот класс является entity нашего курса, вам предстоит доделать его, шобы он
     имел в себе не только список пользователей, присоединенных к нему, но и тесты и учебный материал
     */
}
