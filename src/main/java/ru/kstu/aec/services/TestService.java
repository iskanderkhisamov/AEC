package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.models.User;
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

    public List<Test> findAllbyAuthor(User user) {
        return testRepository.findAllByAuthor(user);
    }

    @Transactional
    public void saveTest(Test test) {
        testRepository.save(test);
    }

    @Transactional
    public void deleteTest(Test test) {
        testRepository.delete(test);
    }

}
