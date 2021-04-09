package ru.kstu.aec.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.User;

import java.util.Optional;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}