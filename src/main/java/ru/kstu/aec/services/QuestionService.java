package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Question;
import ru.kstu.aec.repositories.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> loadQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }
}
