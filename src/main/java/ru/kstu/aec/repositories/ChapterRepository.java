package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kstu.aec.models.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
