package ru.kstu.aec.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String gruppa;

    private boolean teacher;

    private String email;

    private String password;

    @Builder.Default
    private UserRole userRole = UserRole.USER;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_courses",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName = "id"))
    private List<Course> courses;
    /*
     каскад это типа те действия, которые ты можешь делать с этой таблицей
     name это название реляционной таблицы в бд
     joinColumns это типа ссылка на тот id, который ему пренадлежит в реляционной таблице, а
     inverseJoinColumns это ссылка на id курса
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*
     реализация юзера
     */
}