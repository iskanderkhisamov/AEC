package ru.kstu.aec.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.User;
import ru.kstu.aec.models.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ru.kstu.aec.configs.SecurityConfig.getAuthentication;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.orElseThrow(() ->
                new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
    }

    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        if(user.isTeacher()) {
            user.setUserRole(UserRole.TEACHER);
            user.setGruppa("Нет");
        }
       saveUser(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public List<Course> getCourses(Course course, User user) {
        List<Course> courses = userRepository.findById(user.getId()).orElseThrow(() ->
                new UsernameNotFoundException(MessageFormat.format("User with id {0} cannot be found.", user.getId()))).getCourses();
        courses.add(course);
        return courses;
    }

    public void addCourse(List<Course> courses, User user) {
        User tuser = user;
        tuser.setCourses(courses);
        saveUser(tuser);
    }
}