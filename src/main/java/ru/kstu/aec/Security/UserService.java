package ru.kstu.aec.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kstu.aec.dao.RoleDAO;
import ru.kstu.aec.dao.UserDAO;
import ru.kstu.aec.models.Role;
import ru.kstu.aec.models.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UserDAO userDao;

    private final RoleDAO roleDao;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDAO userDao, RoleDAO roleDao, PasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.show(1L).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists")));
        user.setRoles(roles);
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
    }
}
