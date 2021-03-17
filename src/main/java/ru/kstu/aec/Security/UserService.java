package ru.kstu.aec.Security;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kstu.aec.models.UserRegistrationDto;


public interface UserService extends UserDetailsService {
    void save(UserRegistrationDto registrationDto);
}
