package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.Question;
import ru.kstu.aec.repositories.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> loadQuestions() {
        return questionRepository.findAll();
    }

    @SneakyThrows
    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.orElseThrow(() -> new Exception("Такого вопроса нет, id = " + id));
    }

    @Transactional
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
}
