package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Answer;
import ru.kstu.aec.models.Theme;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query(value = "SELECT * FROM theme WHERE course_id = ?1", nativeQuery = true)
    List<Theme> getByCourse(Long id);
}
