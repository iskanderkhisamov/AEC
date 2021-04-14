package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Course;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> loadCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Transactional
    public void createCourse(Course course) {
        courseRepository.save(course);
    }
}
