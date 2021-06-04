package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.Statistic;
import ru.kstu.aec.repositories.CourseRepository;
import ru.kstu.aec.repositories.StatisticRepository;

@Service
public class CourseService {

    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}
