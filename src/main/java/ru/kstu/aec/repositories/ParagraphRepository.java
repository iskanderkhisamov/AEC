package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Paragraph;
import ru.kstu.aec.models.Theme;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {
}
