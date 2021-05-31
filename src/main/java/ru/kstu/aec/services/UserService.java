package ru.kstu.aec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.User;
import ru.kstu.aec.repositories.UserRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> loadUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);
        // получаем юзера по мылу и в зависимости от того, нашли мы его в бд или нет выводим ошибку или возвращаем юзера
        return optionalUser.orElseThrow(() ->
                new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
    }

    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        try {
            loadUserByUsername(user.getEmail());
            // чекаем является ли учителем наш юзер, меняем группу
            System.out.println("email уже существует");
        }
        catch(Exception e) {
            saveUser(user);
        }
        // сохраняем пользователя в бд
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void changeUserPassword(User user, String password) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        saveUser(user);
    }

    public void changeUserFirstName(User user, String firstName) {
        user.setFirstname(firstName);
        saveUser(user);
    }

    public void changeUserSecondName(User user, String secondName) {
        user.setSurname(secondName);
        saveUser(user);
    }

    public void changeUserEmail(User user, String email) {
        user.setEmail(email);
        saveUser(user);
    }
/*
    @Transactional
    public List<Course> getCourses(Course course, User user) {
        List<Course> courses = userRepository.findById(user.getId()).orElseThrow(() ->
                new UsernameNotFoundException(MessageFormat.format("User with id {0} cannot be found.", user.getId()))).getCourses();
        courses.add(course);
        // получаем курсы текущего пользователя и вставляем курс из параметров в них
        return courses;
    }

 */
/*
    public void addCourse(List<Course> courses, User user) {
        User user1 = user;
        user1.setCourses(courses);
        saveUser(user1);
        // добавляем курсы в юзера и сохраняем его
    }

 */
}