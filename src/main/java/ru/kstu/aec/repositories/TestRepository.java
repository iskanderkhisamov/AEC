package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kstu.aec.models.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
