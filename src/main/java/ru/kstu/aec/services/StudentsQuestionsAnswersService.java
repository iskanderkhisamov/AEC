package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.StudentsQuestionsAnswers;
import ru.kstu.aec.repositories.StudentsQuestionsAnswersRepository;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentsQuestionsAnswersService {
    private final StudentsQuestionsAnswersRepository repository;

    public List<StudentsQuestionsAnswers> loadStudentsQuestionsAnswers() {
        return (List<StudentsQuestionsAnswers>) repository.findAll();
    }

    @Transactional
    public void createStudentsQuestionsAnswers(StudentsQuestionsAnswers studentsQuestionsAnswers) {
        repository.save(studentsQuestionsAnswers);
    }

    public StudentsQuestionsAnswers getStudentsQuestionsAnswers(Long id) {
        return repository.getOne(id);
    }

    public void save(StudentsQuestionsAnswers studentsQuestionsAnswers) {
        repository.save(studentsQuestionsAnswers);
    }
}
