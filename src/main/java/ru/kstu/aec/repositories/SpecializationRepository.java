package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    @Query(value = "SELECT name FROM specialization WHERE id = ?1", nativeQuery = true)
    String getName(Long id);
}
