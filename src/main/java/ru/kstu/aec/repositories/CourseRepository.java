package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT specialization.name FROM course JOIN specialization ON course.specializationId = specialization.id", nativeQuery = true)
    List<String> getfdsf();
}
