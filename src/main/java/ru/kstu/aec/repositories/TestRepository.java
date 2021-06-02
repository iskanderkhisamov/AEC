package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.models.User;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findAllByAuthor(User user);

}
