package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Answer;
import ru.kstu.aec.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
