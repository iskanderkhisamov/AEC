package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Role;
import ru.kstu.aec.repositories.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role loadRoleById(Long id) throws Exception {
        final Optional<Role> role = roleRepository.findById(id);
        return role.orElseThrow(() ->
                new Exception());
    }
}
