package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
