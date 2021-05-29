package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kstu.aec.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
