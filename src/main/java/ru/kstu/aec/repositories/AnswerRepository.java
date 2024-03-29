package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
