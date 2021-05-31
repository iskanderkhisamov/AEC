package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsQuestionsAnswersRepository extends JpaRepository<StudentsQuestionsAnswers, Long> {
}
