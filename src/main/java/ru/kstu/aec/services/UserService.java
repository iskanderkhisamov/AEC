package ru.kstu.aec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return optionalUser.orElseThrow(() ->
                new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
    }

    @Transactional
    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        try {
            loadUserByUsername(user.getEmail());
            System.out.println("email уже существует");
        } catch (Exception e) {
            saveUser(user);
        }
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void changeUserPassword(User user, String password) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        saveUser(user);
    }

    @Transactional
    public void changeUserFirstName(User user, String firstName) {
        user.setFirstname(firstName);
        saveUser(user);
    }

    @Transactional
    public void changeUserSecondName(User user, String secondName) {
        user.setSurname(secondName);
        saveUser(user);
    }

    @Transactional
    public void changeUserEmail(User user, String email) {
        user.setEmail(email);
        saveUser(user);
    }

}