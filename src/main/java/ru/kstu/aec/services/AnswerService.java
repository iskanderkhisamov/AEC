package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Answer;
import ru.kstu.aec.models.Chapter;
import ru.kstu.aec.repositories.AnswerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public List<Answer> loadAnswers() {
        return answerRepository.findAll();
    }

    @Transactional
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @SneakyThrows
    public Answer getAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.orElseThrow(() -> new Exception("Такого ответа нет, id = " + id));
    }

    @Transactional
    public void deleteAnswer(Answer answer) {
        answerRepository.delete(answer);
    }

}
