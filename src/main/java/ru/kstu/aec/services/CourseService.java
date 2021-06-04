package ru.kstu.aec.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.User;
import ru.kstu.aec.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @SneakyThrows
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElseThrow(() -> new Exception("Такого курса нет, id = " + id));
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> getCoursesByUser(User user) {
        return courseRepository.findCoursesByUser(user);
    }
}
