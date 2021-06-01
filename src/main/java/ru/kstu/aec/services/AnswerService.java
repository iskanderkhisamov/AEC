package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Answer;
import ru.kstu.aec.models.Question;
import ru.kstu.aec.repositories.AnswerRepository;
import ru.kstu.aec.repositories.QuestionRepository;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public List<Answer> loadAnswers() {
        return (List<Answer>) answerRepository.findAll();
    }

    @Transactional
    public void createAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswer(Long id) throws Exception {
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.orElseThrow(() -> new Exception("Такого ответа нет, id = " + id));
    }

}
