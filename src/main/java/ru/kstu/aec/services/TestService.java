package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.repositories.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test getTest(Long id) throws Exception {
        Optional<Test> test = testRepository.findById(id);
        return test.orElseThrow(() -> new Exception("Такого теста нет, id = " + id));
    }

    public List<Test> loadTests() {
        return testRepository.findAll();
    }

    public void saveTest(Test test) {
        testRepository.save(test);
    }

}
