package ru.kstu.aec.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Chapter;
import ru.kstu.aec.repositories.ChapterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    final ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public List<Chapter> getChapters() {
        return chapterRepository.findAll();
    }

    @SneakyThrows
    public Chapter getChapter(Long id) {
        Optional<Chapter> chapter = chapterRepository.findById(id);
        return chapter.orElseThrow(() -> new Exception("Такого раздела нет, id = " + id));
    }

    @Transactional
    public void saveChapter(Chapter chapter) {
        chapterRepository.save(chapter);
    }
}
