package ru.kstu.aec.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // эта штука сама создает методы для связи с бд, но вы можете
    // добавить кастомные, как в CourseRepository, если есть доп вопросы, спрашивайте
}
