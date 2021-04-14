package ru.kstu.aec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
