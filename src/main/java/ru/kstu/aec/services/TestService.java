package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Question;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.repositories.TestRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TestService {
    private final TestRepository testRepository;

    public List<Test> loadTests() {
        return testRepository.findAll();
    }

    public Test getTest(Long id) throws Exception {
        Optional<Test> test = testRepository.findById(id);
        return test.orElseThrow(() -> new Exception("Такого теста нет, id = " + id));
    }

    @Transactional
    public void createTest(Test test) {
        testRepository.save(test);
    }
}
