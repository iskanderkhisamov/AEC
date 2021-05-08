package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
