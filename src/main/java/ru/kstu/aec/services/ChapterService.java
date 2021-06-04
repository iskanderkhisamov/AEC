package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Chapter;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.repositories.ChapterRepository;
import ru.kstu.aec.repositories.CourseRepository;

@Service
public class ChapterService {

    final ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Transactional
    public void saveChapter(Chapter chapter) {
        chapterRepository.save(chapter);
    }
}
