package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Answer;
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
    public void createAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswer(Long id) throws Exception {
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.orElseThrow(() -> new Exception("Такого ответа нет, id = " + id));
    }

    public Answer getAnswer() throws Exception {
        List<Answer> answers = answerRepository.findAll();
        Answer answer = answers.get(0);
        for (Answer ans : answers) {
            answer = ans.getId() > answer.getId() ? ans : answer;
        }
        return answer;
    }

}
